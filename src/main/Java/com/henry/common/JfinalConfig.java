package com.henry.common;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.henry.Interceptor.LoginSessionInterceptor;
import com.henry.common.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.json.FastJson;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;

import java.sql.Connection;

public class JfinalConfig extends JFinalConfig {

    // 先加载开发环境配置，再追加生产环境的少量配置覆盖掉开发环境配置
    private static Prop p = PropKit.use("jfinalconfig")
            .appendIfExists("jfinalpro.txt");

    private WallFilter wallFilter;
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(p.getBoolean("devMode", false));
        me.setJsonFactory(FastJson::new);

        // 支持 Controller、Interceptor 之中使用 @Inject 注入业务层，并且自动实现 AOP
        me.setInjectDependency(true);
        me.setBaseUploadPath("/upload");


    }

    @Override
    public void configRoute(Routes me) {
        me.add(new FrontRoute());
        me.add(new AdminRoute());


    }
    /**
     * 配置模板引擎，通常情况只需配置共享的模板函数
     */
    @Override
    public void configEngine(Engine me) {



    }

    @Override
    public void configPlugin(Plugins me) { DruidPlugin druidPlugin = getDruidPlugin();
        wallFilter = new WallFilter();              // 加强数据库安全
        wallFilter.setDbType("mysql");
        druidPlugin.addFilter(wallFilter);
        druidPlugin.addFilter(new StatFilter());    // 添加 StatFilter 才会有统计数据
        me.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        me.add(arp);
        _MappingKit.mapping(arp);
        arp.setShowSql(p.getBoolean("devMode", true));
        arp.getEngine().setToClassPathSourceFactory();
        arp.addSqlTemplate("/sql/sqls.sql");
        me.add(new EhCachePlugin());

    }

    /**配置全局拦截器
     * */
    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LoginSessionInterceptor());

    }

    @Override
    public void configHandler(Handlers me) {

    }
    /**
     * 抽取成独立的方法，便于 _Generator 中重用该方法，减少代码冗余
     */
    public static DruidPlugin getDruidPlugin() {
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
    }


}
