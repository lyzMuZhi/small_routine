package com.shestnut.user.internal.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge != 0) {
            cookie.setMaxAge(maxAge);
        }
        if (domain != null && !"".equalsIgnoreCase(domain)) {
            cookie.setDomain(domain);
        }

        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        if (domain != null && !"".equalsIgnoreCase(domain)) {
            cookie.setDomain(domain);
        }

        response.addCookie(cookie);
    }

    public static Cookie getCookie(Cookie cookies[], String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        return getCookie(cookies, name);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = getCookie(cookies, name);
        if (cookie == null) {
            return null;
        } else {
            return cookie.getValue();
        }
    }

    public static String getCookieValue(Cookie[] cookies, String name) {

        if (cookies == null || cookies.length == 0 || StringUtil.isBlank(name)) {
            return null;
        }

        String value = null;
        for (Cookie cookie : cookies) {
            if (!name.equals(cookie.getName())) {
                continue;
            }
            value = cookie.getValue();
        }

        return value;
    }

    /**
     * 构造 Cookie 的通用方法<br>
     *
     * @param name
     * @param value
     * @param maxAge
     * @param domain
     * @return
     */
    public static Cookie buildCookie(String name, String value, int maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(true);
        cookie.setPath("/");
        if (maxAge != 0) {
            cookie.setMaxAge(maxAge);
        }
        if (domain != null && !"".equalsIgnoreCase(domain)) {
            cookie.setDomain(domain);
        }
        return cookie;
    }
}
