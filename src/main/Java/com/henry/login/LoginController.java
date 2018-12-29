package com.henry.login;


import com.henry.common.kit.IpKit;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;


public class LoginController  extends Controller {

    LoginService srv = LoginService.me;
    public void index(){
        render("login.html");
    }


    /**
     * 登录
     * */
    @Before(LoginValidator.class)
    public void doLogin(){
        String username = getPara("username");
        String password = getPara("password");
        System.out.println(username);
        String ip = IpKit.getRealIp(getRequest());
        Ret ret = srv.login(username,password,ip);
        /*
        * 登录校验成功
        * */
        if(ret.isOk()){
            String sessionId = ret.getStr(LoginService.sessionIdName);
            int maxAgeInseconds = ret.getInt("maxAgeInSeconds");
            setCookie(LoginService.sessionIdName,sessionId,maxAgeInseconds,true);
            setAttr(LoginService.loginAccountCacheName,ret.get(LoginService.loginAccountCacheName));

        }
        renderJson(ret);

    }
    @Clear
    @ActionKey("/logout")
    public void logout(){
        removeCookie(LoginService.sessionIdName);
        setAttr(LoginService.sessionIdName,null);
        redirect("/");
    }

    //二维码生成
    public void captcha(){
        renderCaptcha();
    }
}
