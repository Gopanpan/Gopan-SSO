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
     * @param sysUserId
     * @return
     */
    @RequestMapping(value = "/andOrupdateSysUserView", method = RequestMethod.GET)
    public ModelAndView andOrupdateSysUserView(String sysUserId){
        Map<String, Object> map = new HashMap<>();
        map.put("sysUserId", sysUserId);
        return new ModelAndView("sysuser/systemUser", map);
    }

    /**
     * 系统列表
     * @return
     */
    @RequestMapping(value = "/sysInfoListView",method = RequestMethod.GET)
    public String sysInfoListView(){
        return "sysinfo/sysInfoList";
    }
}
