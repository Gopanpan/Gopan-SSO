package com.horse.sso.common.utils;

import javax.servlet.http.Cookie;

/**
 * Created by lzb.
 */
public class CookieUtils {

    /**
     * 根据cookie名称获取cookie值
     *
     * @param cookies    cookie列表
     * @param cookieName cookie名称
     * @return 该cookie名称对应值
     */
    public static String getCookieByName(Cookie[] cookies, String cookieName) {

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
