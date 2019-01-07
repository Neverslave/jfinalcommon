package com.henry.nav;

import com.henry.common.BaseController;
import com.henry.common.model.Nav;
import com.jfinal.kit.JsonKit;

import java.util.List;


/**
* 侧边导航栏
* */
public class NavController extends BaseController {
    NavService srv = NavService.me;
    public void index(){
        List<Nav> navList = srv.getNavs();
        String nav = JsonKit.toJson(navList); //转为json字符串
        System.out.println(nav);
        renderJson(nav);
    }
}
