package bing.Pan.sso.manage.business.controller;

import bing.Pan.sso.common.response.Response;
import bing.Pan.sso.domain.vObject.SystemUserVo;
import bing.Pan.sso.manage.business.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 19:23
 * @desc : SSO系统管理用户相关
 */

@Api(value = "SystemUserController", description = "系统用户管理接口")
@RestController
public class SystemUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired SystemUserService systemUserService;


    /**
     * 系统管理用户列表
     * @param systemUserVo
     * @return
     */
    @ApiOperation(value = "系统管理用户列表")
    @RequestMapping(value = "/systemUserList", method = RequestMethod.POST)
    public Object systemUserList(@ModelAttribute SystemUserVo systemUserVo){
        return new Response(systemUserService.systemUserList(systemUserVo));
    }


    public Object getSystemUserById(@ModelAttribute SystemUserVo systemUserVo){
        return new Response<>(systemUserService.getSystemUserById(systemUserVo));

    }

}
