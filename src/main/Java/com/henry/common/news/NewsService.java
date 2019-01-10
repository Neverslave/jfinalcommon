package com.henry.common.news;

import com.henry.common.model.News;
import com.henry.common.model.Newslist;
import com.jfinal.plugin.activerecord.Db;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻列表及新闻详情类
 * */
public class NewsService {
    private  News NewsDao = new News().dao();
    private Newslist newslistDao = new Newslist().dao();
    public  static  NewsService me  = new NewsService();

    /**
     * 获得新闻列表
     * */
    public List<Newslist> getNewslists(){
       List<Newslist> newslists = new ArrayList<>() ;
        newslists = newslistDao.find("select * from newslist order by newsTime desc ");
        return  newslists;
    }
    /***
     * 根据前台数据范围查询数量
     *
     * */
    public List<Newslist> getNewslists(int number){
        String sql = Db.getSql("news.getNewsLists");
        List<Newslist> newslists = newslistDao.find(sql,number);
        return newslists;
    }



}
