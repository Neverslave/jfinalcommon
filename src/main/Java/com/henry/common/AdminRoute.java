package com.henry.common;

import com.henry.admin.AdminIndexController;
import com.henry.login.LoginController;
import com.henry.nav.NavController;
import com.jfinal.config.Routes;

public class AdminRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/admin/page");
        add("/admin", AdminIndexController.class);
        add("/login", LoginController.class);
        add("/admin/nav", NavController.class);

    }
}
