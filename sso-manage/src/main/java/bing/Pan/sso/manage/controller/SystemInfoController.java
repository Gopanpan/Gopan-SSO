package bing.Pan.sso.manage.controller;

import bing.Pan.sso.common.response.Response;
import bing.Pan.sso.domain.vObject.BaseVo;
import bing.Pan.sso.service.SystemInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/21 9:43
 * @desc : 系统相关controller
 */

@Api(value = "SystemUserController", description = "系统管理接口")
@RestController
public class SystemInfoController {


    @Autowired
    SystemInfoService systemInfoService;

    @ApiOperation(value = "系统管理用户列表")
    @RequestMapping(value = "/getSysInfoPageList", method = RequestMethod.POST)
    public Object getSysInfoPageList(@ModelAttribute BaseVo baseVo){
        return new Response(systemInfoService.getSysInfoPageList(baseVo));
    }


}
