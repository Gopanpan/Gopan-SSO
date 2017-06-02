package bing.Pan.sso.manage.controller;

import bing.Pan.sso.common.constant.ExportConstantData;
import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import bing.Pan.sso.common.response.Response;
import bing.Pan.sso.common.utils.ExcelExportTools;
import bing.Pan.sso.domain.bussinessobject.SystemUserAddBo;
import bing.Pan.sso.domain.bussinessobject.SystemUserBo;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.service.SystemUserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 19:23
 * @desc : SSO系统管理用户相关
 */

@Api(value = "SystemUserController", description = "系统用户管理接口")
@RestController
@RequestMapping("/sysUser")
public class SystemUserController extends BaseController{

    @Autowired private SystemUserService systemUserService;

    /**
     * 系统管理用户列表
     * @param systemUserBo
     * @return
     */
    @ApiOperation(value = "系统管理用户列表",  response = Response.class)
    @RequestMapping(value = "/systemUserList", method = RequestMethod.POST)
    public Object systemUserList(@Valid @ModelAttribute SystemUserBo systemUserBo,
                                 BindingResult result) throws Exception {
        checkValid(result);

        return new Response<>(systemUserService.findPageListByE(systemUserBo));
    }


    @ApiOperation(value = "根据系统管理用户ID获取详情", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "系统用户Id", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value = "/getSystemUserById", method = RequestMethod.POST)
    public Object getSystemUserById(Long id) throws Exception {
        if(ObjectUtils.isEmpty(id))
            throw new ServiceException(ResponseCode.CLIENT_PARAM_MISS,"传入的系统用户ID为空!");

        return new Response<>(systemUserService.selectById(id));

    }

    @ApiOperation(value = "添加系统管理员账户")
    @RequestMapping(value = "/addAUPdateSysUser", method = RequestMethod.POST)
    public Object addSysUser(@Valid @ModelAttribute SystemUserAddBo userAddBo, BindingResult result) throws Exception {
        checkValid(result);

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userAddBo,sysUser);
        systemUserService.insertOrUpdate(sysUser, getCurrentUser());

        return new Response<>();

    }

    /**
     * 此方法为结合 使用bootstrapvalidator的remote验证
     * 这里需要说明的是bootstrap的remote验证器需要的返回结果一定是json格式的数据 ,key 值切必须为 valid
     * {"valid":false} //表示不合法，验证不通过
     * {"valid":true} //表示合法，验证通过
     * @param loginName
     * @return
     */
    @ApiOperation(value = "添加系统管理员是校验登陆名是否重复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登陆名", required = true, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/checkLoginName", method = RequestMethod.POST)
    public Object checkLoginName(String loginName) throws Exception {

        Map<String, Boolean> result = Maps.newHashMap();
        SysUser sysUser = systemUserService.findByLoginName(loginName);
        if(ObjectUtils.isEmpty(sysUser))
            result.put("valid",true);
        else
            result.put("valid",false);

        return result;

    }


    @ApiOperation(value = "删除系统用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUserId",value = "系统用户Id",required = true,dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Object deleteUser(Long sysUserId) throws Exception {
        if(StringUtils.isEmpty(sysUserId))
            throw new ServiceException(ResponseCode.CLIENT_PARAM_MISS,"系统用户ID为空!");

        systemUserService.deleteById(sysUserId);

        return new Response<>();
    }


    @ApiOperation(value = "导出系统用户")
    @RequestMapping(value = "/downloadSysUser",method = RequestMethod.POST)
    public void downloadSysUser( @ModelAttribute SystemUserBo systemUserBo,HttpServletResponse response) throws Exception {

        List<SysUser> list = systemUserService.findList(systemUserBo);
        String[] filterField = new String[]{"id"};
        new ExcelExportTools("系统用户导出数据","导出数据",ExportConstantData.sysUserheaderList(),
                ExportConstantData.sysUserColumnWidth(),list.size()).setDataList(list,filterField).write(response,"系统用户导出数据.xlsx")
                .dispose();
    }

}
