package com.henry.Admin;

import com.henry.Interceptor.AdminInterceptor;
import com.henry.common.BaseController;
import com.jfinal.aop.Before;

@Before(AdminInterceptor.class)
public class AdminIndexController extends BaseController {
    public void index(){
        render("index.html");
    }
}
