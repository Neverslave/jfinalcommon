package com.henry.login;

import com.henry.common.BaseController;

public class LoginController  extends BaseController {



    public void index(){
        render("adminIndex.html");

    }




    //二维码生成
    public void captcha(){
        renderCaptcha();

    }
}
