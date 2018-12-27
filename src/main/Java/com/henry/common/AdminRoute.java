package com.henry.common;

import com.henry.Admin.AdminIndexController;
import com.henry.login.LoginController;
import com.jfinal.config.Routes;

public class AdminRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/admin/page");
        add("/admin", AdminIndexController.class);
        add("/login", LoginController.class);
    }
}
