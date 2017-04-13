package bing.Pan.sso.manage.common.Interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/4/5 15:23
 * @desc :
 */

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    private final String[] noFilters = new String[]{"/","/login","/loginSysUser"};

    @Value("${server.context-path}")
    private String basePath;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       /* response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Accept");*/

        String url = request.getRequestURI();for (String no : noFilters) {
            if (url.endsWith(String.format("%s%s", basePath, no))) {
                return true;
            }
        }

        //执行这里表示用户身份需要认证，跳转登陆页面
        HttpSession session = request.getSession();
        if(!ObjectUtils.isEmpty(session.getAttribute("user")))return true;
        response.sendRedirect(request.getContextPath());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
