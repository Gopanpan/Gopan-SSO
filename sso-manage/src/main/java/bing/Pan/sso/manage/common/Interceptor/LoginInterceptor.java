package bing.Pan.sso.manage.common.Interceptor;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.response.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        String url = request.getRequestURI();
        for (String noFilter : noFilters) {
            if (url.endsWith(noFilter)) {
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



    private boolean dealResult(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Accept");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(new Response<>(ResponseCode.LOGIN_NOT_LOGIN)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }


}
