package com.henry.Interceptor;

import com.henry.common.model.Account;
import com.henry.login.LoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;


/**
 * 从 cookie 中获取 sessionId，如果获取到则根据该值使用 LoginService
 * 得到登录的 Account 对象 ---> loginAccount，供后续的流程使用
 *
 * 注意：将此拦截器设置为全局拦截器，所有 action 都需要
 */
public class LoginSessionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        Account loginAccount ;
        Controller c = inv.getController();
        String sessionId = c.getCookie(LoginService.sessionIdName);
        if (sessionId != null) {
            loginAccount = LoginService.me.getLoginAccountWithSessionId(sessionId);
/*            if (loginAccount == null) {
                String loginIp = IpKit.getRealIp(c.getRequest());
                loginAccount = LoginService.me.loginWithSessionId(sessionId, loginIp);
            }*/
            if (loginAccount != null) {
                // 用户登录账号
                c.setAttr(LoginService.loginAccountCacheName, loginAccount);
            } else {
                c.removeCookie(LoginService.sessionIdName); // cookie 登录未成功，证明该 cookie 已经没有用处，删之
            }
        }

        inv.invoke();

    }
}
