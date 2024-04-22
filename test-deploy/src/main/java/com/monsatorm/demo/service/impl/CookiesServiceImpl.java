package com.monsatorm.demo.service.impl;
import com.monsatorm.demo.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CookiesServiceImpl implements CookieService {

    @Override
    public String checkUserCookies(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> sessionIdCookie = Arrays.stream(request.getCookies() != null ? request.getCookies() : new Cookie[0])
                .filter(cookie -> "sessionId".equals(cookie.getName()))
                .findFirst();

        if (sessionIdCookie.isPresent()) {
            return sessionIdCookie.get().getValue();

        } else {
            String newSessionId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("sessionId", newSessionId);
            cookie.setHttpOnly(true);
            int COOKIE_EXPIRATION = 7 * 24 * 60 * 60;
            cookie.setMaxAge(COOKIE_EXPIRATION);
            cookie.setPath("/");
            response.addCookie(cookie);
            return newSessionId;
        }

    }
}
