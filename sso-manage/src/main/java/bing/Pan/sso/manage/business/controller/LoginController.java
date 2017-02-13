package bing.Pan.sso.manage.business.controller;

import bing.Pan.sso.common.response.Response;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @crea :Created by intelliJ IDEA 16.1.3
 * @auth :bing.Pan 15923508369@163.com
 * @date :2017/1/17 18:58
 * @desc :
 */

@Api(value = "LoginController", description = "登录、登出")
@Controller
public class LoginController {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginSysUser", method = RequestMethod.POST)
    @ResponseBody
    public Object loginSysUser(@RequestParam(required = true)  String userName,
                               @RequestParam(required = true)  String password){


        return new Response();


    }


}
