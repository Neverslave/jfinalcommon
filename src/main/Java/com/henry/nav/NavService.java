package com.henry.nav;

import com.henry.common.model.Nav;
import com.jfinal.kit.Ret;

import java.util.List;

public class NavService {
    public static  NavService me = new NavService();
    private Nav navDao = new Nav().dao();


    /**
     * 返回所有根节点
     * */
    public List<Nav> getParentNav(){
        List<Nav>  navList= navDao.find("select * from nav where parentId is NULL ");
        return navList;
    }

    /**
     * 子节点加到父节点中children字段中。
     * */
    public List<Nav> getNavs(){
        List<Nav> childrenNavList =navDao.find("select * from nav where parentId != '' ");
         List<Nav>navList = getParentNav();
        for (int i = 0; i <childrenNavList.size() ; i++) {
          Integer id = childrenNavList.get(i).getParentId();
            for (int j = 0; j < navList.size(); j++) {
                if (navList.get(j).getId().equals(id)) {
                    navList.get(j).getChildren().add(childrenNavList.get(i));
                }
            }
        }
        return  navList;
    }

    //todo 后续考虑增加动态配置
    public Ret addNav(Nav nav){
        nav.update();
        return  Ret.ok("msg","增加成功");
    }










}
