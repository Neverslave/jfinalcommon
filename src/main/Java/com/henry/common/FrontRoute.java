package com.henry.common;

import com.henry.index.IndexController;
import com.jfinal.config.Routes;

public class FrontRoute extends Routes {
    @Override
    public void config() {
        add("/", IndexController.class);
    }
}
