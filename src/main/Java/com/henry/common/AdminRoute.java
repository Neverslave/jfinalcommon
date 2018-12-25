package com.henry.common;

import com.henry.Admin.AdminIndexController;
import com.jfinal.config.Routes;

public class AdminRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/admin"); //设置页面文件基础路径
        add("/admin", AdminIndexController.class);
    }
}
