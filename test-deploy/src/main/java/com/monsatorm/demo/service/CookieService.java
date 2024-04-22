package com.monsatorm.demo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface CookieService {
    String  checkUserCookies(HttpServletRequest request, HttpServletResponse response);
}
