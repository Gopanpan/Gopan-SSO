package bing.Pan.sso.manage.business;

import bing.Pan.sso.domain.pObject.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 18:58
 * @desc :
 */

@Controller
public class LoginController {


    /**
     * 登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";

    }
    @RequestMapping("/index")
    public String index(){
        return "index";

    }

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

    /**
     * 系统主页面
     * @return
     */
    @RequestMapping(value = "/ssoMain", method = RequestMethod.GET)
    public Object ssoMain(){
        return "manager";
    }


}
