import sys
import urllib.parse
import socket
import re

def execute_web_request(target_url):
    try:
        url_elements = urllib.parse.urlparse(target_url)
        server_host = url_elements.netloc
        server_path = url_elements.path if url_elements.path else '/'
        server_port = 443 if url_elements.scheme == 'https' else 80

        with socket.create_connection((server_host, server_port)) as connection_socket:
            request_header = f"GET {server_path} HTTP/1.1\r\nHost: {server_host}\r\nConnection: close\r\n\r\n"
            connection_socket.sendall(request_header.encode())
            full_response = b""
            while True:
                data_chunk = connection_socket.recv(4096)
                if not data_chunk:
                    break
                full_response += data_chunk

        decoded_response = full_response.decode()

        content_start = decoded_response.find('\r\n\r\n')
        if content_start != -1:
            decoded_response = decoded_response[content_start + 4:]
            return decoded_response
        else:
            raise Exception("Content not found in HTTP response")
    except Exception as exception_detail:
        return None

def remove_html_tags(html_string):
    without_style_tags = re.sub(r'<style[\s\S]*?</style>', '', html_string)
    plain_text = re.sub(r'<[^>]*>', '', without_style_tags)
    return plain_text

def perform_search(search_keywords):
    try:
        google_search_url = f"https://www.google.com/search?q={urllib.parse.quote(' '.join(search_keywords))}"
        print("Constructed Search URL:", google_search_url)

        search_result = execute_web_request(google_search_url)

        if search_result:
            print("Initial 10 search outcomes:")
            print('\n'.join(search_result.split('\n')[:10]))
        else:
            print("Search results retrieval failed.")
    except Exception as search_error:
        print(f"Search operation encountered an error: {search_error}")

def main_program():
    if len(sys.argv) < 2 or sys.argv[1] == '-h':
        print("Usage: web_fetcher -u <URL> | web_fetcher -s <search-terms>")
        sys.exit(1)

    command_option = sys.argv[1]

    if command_option == '-u':
        if len(sys.argv) < 3:
            print("Error: URL expected after -u option")
            sys.exit(1)
        target_url = sys.argv[2]
        webpage_response = execute_web_request(target_url)
        if webpage_response:
            readable_content = remove_html_tags(webpage_response)
            print(readable_content)
        else:
            print("URL content could not be retrieved.")
    elif command_option == '-s':
        if len(sys.argv) < 3:
            print("Error: Search terms expected after -s option")
            sys.exit(1)
        perform_search(sys.argv[2:])
    else:
        print("Error: Invalid option. Please use -u for URLs or -s for searching terms.")
        sys.exit(1)

if __name__ == "__main__":
    main_program()
