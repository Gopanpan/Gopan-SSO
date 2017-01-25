package bing.Pan.sso.manage.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共拦截器 Created by god on 16/9/6.
 */
@Configuration
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
        throws Exception {
        //httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
//
//        if (modelAndView == null) {
//            return;
//        }
//        modelAndView.addObject("ctx", httpServletRequest.getContextPath());
//        modelAndView.addObject("version", System.currentTimeMillis());
//


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
