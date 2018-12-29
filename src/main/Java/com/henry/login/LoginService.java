package com.henry.login;

import com.henry.common.model.Account;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import java.util.Date;

/**
* 登录服务类
* */
public class LoginService {
    private  Account accountDao = new Account().dao();
    public static final LoginService me = new LoginService();

    // 存放登录用户的 cacheName
    public static final String loginAccountCacheName = "loginAccount";

    // "myBlog" 仅用于 cookie 名称，其它地方如 cache 中全部用的 "sessionId" 来做 key
    public static final String sessionIdName = "myBlog";

    /**
     * 登录成功返回 sessionId 与 loginAccount，否则返回一个 msg
     */
    public Ret login(String userName, String password, String loginIp){
        Account loginAccount = accountDao.findFirst("select * from account where userName = ? limit 1",userName);

        if(loginAccount == null){
            return Ret.fail("msg","用户名或密码不正确");
        }

        String salt = loginAccount.getSalt();
        String hashedPass = HashKit.sha256(salt+password);
        System.out.println(hashedPass);
        String pwd = loginAccount.getPassword();
        //密码错误
        if(!pwd.equals(hashedPass) ){
            return Ret.fail("msg","用户名或密码不正确");

        }

        String sessionId = StrKit.getRandomUUID();

        long liveSeconds = 120*60;
        int maxAgeInSeconds = (int)liveSeconds;
        long expireAt = System.currentTimeMillis()+(liveSeconds*1000);

        loginAccount.removeSensitiveInfo();                                 // 移除 password 与 salt 属性值
        loginAccount.put("sessionId", sessionId);// 保存一份 sessionId 到 loginAccount 备用
        CacheKit.put(loginAccountCacheName, sessionId, loginAccount); //放到缓存中
        createLoginLog(loginAccount.getId(),loginIp);

        return Ret.ok(sessionIdName, sessionId)
                .set(loginAccountCacheName, loginAccount)
                .set("maxAgeInSeconds", maxAgeInSeconds);   // 用于设置 cookie 的最大存活时间

    }



    //创建登陆日志
   public void  createLoginLog(Integer accountId, String loginIp){
       Record loginLog = new Record().set("accountId",accountId).set("ip",loginIp).set("loginTime",new Date());
       Db.save("loginLog",loginLog);

    }
    public Account getLoginAccountWithSessionId(String sessionId) {
        return CacheKit.get(loginAccountCacheName, sessionId);
    }


}
