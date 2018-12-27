package com.henry.login;

import com.henry.common.BaseController;
import com.henry.common.kit.IpKit;
import com.jfinal.aop.Before;

public class LoginController  extends BaseController {

    LoginService srv = LoginService.me;
    public void index(){
        render("login.html");
    }

    /**
     * 登录
     * */
    @Before(LoginValidator.class)
    public void login(){
        String username = getAttr("username");
        String password = getAttr("password");
        String ip = IpKit.getRealIp(getRequest());

    }

    //二维码生成
    public void captcha(){
        renderCaptcha();
    }
}
