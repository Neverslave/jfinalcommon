package com.henry.common.news;

import com.henry.common.BaseController;
import com.henry.common.model.Newslist;
import com.jfinal.kit.JsonKit;

import java.util.List;

/***
 * 新闻，文章控制器
 * */
public class NewsController extends BaseController {
    NewsService srv = NewsService.me;
     public void index(){

         List<Newslist> list = srv.getNewslists();
         String newslist = JsonKit.toJson(list); //转为json字符串
         renderJson(newslist);
     }


}
