package com.horse.sso.manage.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.MessageFormat;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/6/5 13:39
 * @desc :
 */

@WebListener
public class SessionListener  implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {


        String id = httpSessionEvent.getSession().getId();
        System.out.println(MessageFormat.format("sessionCreated,sessionid为：{0}",id));


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        String id = httpSessionEvent.getSession().getId();
        System.out.println(MessageFormat.format("sessionDestroyed,sessionid为：{0}",id));

    }
}
