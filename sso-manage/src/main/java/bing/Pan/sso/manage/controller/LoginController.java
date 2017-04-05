package bing.Pan.sso.manage.controller;

import bing.Pan.sso.common.response.Response;
import bing.Pan.sso.service.SystemUserService;
import bing.Pan.sso.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired SystemUserService systemUserService;

    /**
     * 登陆
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginSysUser", method = RequestMethod.POST)
    @ResponseBody
    public Object loginSysUser(@RequestParam(required = true)  String loginName,
                               @RequestParam(required = true)  String password){

        systemUserService.findUserByLoginName(loginName);
        return new Response();


    }


}
