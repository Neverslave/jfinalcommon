package com.henry.Interceptor;

import com.henry.common.model.Account;
import com.henry.login.LoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;


/**
 * 后台管理页面拦截器，未登录重定向
 * */

public class AdminInterceptor implements Interceptor
{
    @Override
    public void intercept(Invocation inv) {
        Controller c = inv.getController();
       Account account = c.getAttr(LoginService.loginAccountCacheName);
       if(account == null){
           c.redirect("/login");
       }






        inv.invoke();

    }
}
