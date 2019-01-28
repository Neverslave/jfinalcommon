package com.henry.common;

import com.henry.admin.AdminIndexController;
import com.henry.common.model.Newslist;
import com.henry.common.news.NewsController;
import com.henry.login.LoginController;
import com.henry.nav.NavController;
import com.henry.upload.UploadController;
import com.jfinal.config.Routes;

public class AdminRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/admin/page");
        add("/admin", AdminIndexController.class);
        add("/login", LoginController.class);
        add("/admin/nav", NavController.class);
        add("/admin/newslist", NewsController.class);
        add("/upload", UploadController.class);


    }
}
