package com.horse.sso.manage.controller;

import com.horse.sso.common.response.Response;
import com.horse.sso.domain.entity.SysUser;
import com.horse.sso.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @crea :Created by intelliJ IDEA 16.1.3
 * @auth :Gopan 15923508369@163.com
 * @date :2017/1/17 18:58
 * @desc :
 */

@Api(value = "LoginController", description = "登录、登出")
@Controller
public class LoginController extends BaseController {

    @Autowired private SystemUserService systemUserService;

    /**
     * 登陆
     * @param loginName 登陆名
     * @param password  密码
     * @return
     */
    @ApiOperation(value = "登陆", notes = "系统管理员登陆接口", response = Response.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginName", value = "登陆名", required = true, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "MD5加密后的密码", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/loginSysUser", method = RequestMethod.POST)
    @ResponseBody
    public Object loginSysUser(@RequestParam String loginName,
                               @RequestParam String password,
                               HttpServletRequest request) throws Exception {

        SysUser byLoginName = systemUserService.findByLoginName(loginName, password,request);
        request.getSession().setAttribute("user",byLoginName);
        return new Response();


    }


}
