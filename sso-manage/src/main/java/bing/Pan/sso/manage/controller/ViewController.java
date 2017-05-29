package bing.Pan.sso.manage.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/8 13:10
 * @desc :
 */


@Api(description = "系统所有的页面跳转页面")
@Controller
public class ViewController {

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String login(){
        return "login";

    }
    /**
     * 系统主页面
     * @return
     */
    @RequestMapping(value = "/ssoMain", method = RequestMethod.GET)
    public Object ssoMain(){
        return "basic";
    }

    /**
     * 系统欢迎页面
     * @return
     */
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String index(){
        return "welcome";

    }


    //----------------------------------------sso系统用户管理页面跳转-------------------------------------

    /**
     * 系统管理用户列表
     * @return
     */
    @RequestMapping(value = "/systemUserListView",method = RequestMethod.GET)
    public String systemUserListView(){
        return "sysuser/systemUserList";
    }

    /**
     * 系统管理用户新增/修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/sysUser/addAUPdateSysUserView", method = RequestMethod.GET)
    public ModelAndView addAUPdateSysUser(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new ModelAndView("sysuser/systemUser", map);
    }

    /**
     * 系统用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/sysUser/sysUserDetailView", method = RequestMethod.GET)
    public ModelAndView sysUserDetailView(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new ModelAndView("sysuser/systemUserDetail", map);
    }





    //------------------------------------应用系统用户管理页面跳转--------------------------


    /**
     * 系统管理用户列表
     * @return
     */
    @RequestMapping(value = "/ssoUserListView",method = RequestMethod.GET)
    public String ssoUserListView(){
        return "ssouser/ssoUserList";
    }

    /**
     * 应用系统管理用户新增/修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/ssoUser/addAUPdateSsoUserView", method = RequestMethod.GET)
    public ModelAndView addAUPdateSsoUserView(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new ModelAndView("ssouser/ssoUser", map);
    }


    /**
     * 应用系统用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/ssoUser/ssoUserDetailView", method = RequestMethod.GET)
    public ModelAndView ssoUserDetailView(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new ModelAndView("ssouser/ssoUserDetail", map);
    }



    //---------------------------应用系统管理页面跳转-------------------------------



    /**
     * 系统管理用户列表
     * @return
     */
    @RequestMapping(value = "/ssoSystemListView",method = RequestMethod.GET)
    public String ssoSystemListView(){
        return "ssoSystem/ssoSystemList";
    }




}
