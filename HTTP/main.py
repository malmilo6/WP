from bs4 import BeautifulSoup
import socket
import ssl
import re
import sys
import hashlib

SECURE_PORT = 443
DEFAULT_PORT = 80
cache_storage = {}


def extract_url_components(full_url):
    scheme_split = full_url.find("://")
    scheme = full_url[:scheme_split] if scheme_split != -1 else "http"
    remaining_url = full_url[scheme_split + 3:] if scheme_split != -1 else full_url

    path_split = remaining_url.find("/")
    host_port, resource_path = remaining_url[:path_split], remaining_url[path_split:] if path_split != -1 else (
        remaining_url, "/")

    hostname, port_number = extract_host_port(host_port)
    return scheme, hostname, port_number, resource_path


def extract_host_port(host_port_str):
    port_delimiter = host_port_str.find(":")
    if port_delimiter != -1:
        hostname_part = host_port_str[:port_delimiter]
        port_part = int(host_port_str[port_delimiter + 1:])
    else:
        hostname_part = host_port_str
        port_part = SECURE_PORT
    return hostname_part, port_part


def generate_cache_key(request_url):
    return hashlib.md5(request_url.encode('utf-8')).hexdigest()


def store_in_cache(request_url, response_data):
    cache_id = generate_cache_key(request_url)
    cache_storage[cache_id] = response_data


def retrieve_from_cache(request_url):
    cache_id = generate_cache_key(request_url)
    return cache_storage.get(cache_id)


def execute_http_request(server_host, server_port, resource_path, redirect_limit=10):
    request_url = f"{server_host}:{server_port}{resource_path}"
    cached_response = retrieve_from_cache(request_url)
    if cached_response:
        print("Using cached data")
        return cached_response

    for attempt in range(redirect_limit):
        with socket.create_connection((server_host, server_port)) as sock:
            if server_port == SECURE_PORT:
                context = ssl.create_default_context()
                context.check_hostname = False
                context.verify_mode = ssl.CERT_NONE
                sock = context.wrap_socket(sock, server_hostname=server_host)

            http_request = f"GET {resource_path} HTTP/1.1\r\nHost: {server_host}\r\nConnection: close\r\n\r\n"
            sock.sendall(http_request.encode())

            response = b"".join(iter(lambda: sock.recv(2048), b""))

        header_content, body_content = response.split(b"\r\n\r\n", 1)
        header_str = header_content.decode('utf-8')
        body_str = body_content.decode('utf-8', 'replace')

        if "Location:" in header_str:
            redirect_url_match = re.search(r"Location: (.+)", header_str)
            if redirect_url_match:
                new_location = redirect_url_match.group(1).strip()
                scheme, server_host, server_port, resource_path = extract_url_components(new_location)
                server_port = SECURE_PORT if scheme == 'https' else DEFAULT_PORT
                continue
            else:
                break

    if attempt == redirect_limit - 1:
        raise Exception(f"Exceeded maximum redirects: {redirect_limit}. Last attempted URL: {request_url}")

    store_in_cache(request_url, (header_str, body_str))
    return header_str, body_str


def html_to_text(html_data):
    soup = BeautifulSoup(html_data, 'html.parser')
    text_output = soup.get_text(separator='\n\n', strip=True)
    return text_output.strip()


def parse_search_response(html_body):
    soup = BeautifulSoup(html_body, 'html.parser')

    final_results = []
    index = 1
    results = soup.find_all('div', class_='egMi0 kCrYT')

    while index <= len(results):
        link = results[index - 1].findChild('a')
        if link:
            url = link.get('href')
            if url.startswith('/url?q='):
                url = url.split('/url?q=')[1].split('&sa=')[0]
            desc = link.get_text()

            final_results.append((index, desc, url))
            index += 1
        else:
            break

    return final_results


def google_search(terms):
    query = '+'.join(term.replace(" ", "+") for term in terms)
    url = f"https://www.google.com/search?q={query}"
    scheme, host, port, path = extract_url_components(url)

    _, body = execute_http_request(host, port, path)
    return parse_search_response(body)


def application_entry():
    if len(sys.argv) < 2:
        print("Incorrect usage. Please see the help: -h")
        sys.exit(1)

    command, *arguments = sys.argv[1:]

    if command == '-u' and arguments:
        page_url = arguments[0]
        scheme, host, port, path = extract_url_components(page_url)
        _, page_body = execute_http_request(host, port, path)
        print(html_to_text(page_body))

    if command == '-h':
        print("Usage: python go2web.py -u <URL> | -s <search-term>")

    elif command == '-s' and arguments:
        search_results = google_search(arguments)
        for position, description, link in search_results:
            print(f"Result {position}: {description}\nURL: {link}\n")

    else:
        print("Unsupported command. Use -h for help.")
        sys.exit(1)


if __name__ == "__main__":
    application_entry()
