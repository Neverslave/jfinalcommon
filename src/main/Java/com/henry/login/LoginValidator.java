package com.henry.login;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
/**
 * 登录参数校验
 * */
public class LoginValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        validateCaptcha("captcha", "captchaMsg", "验证码不正确");
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson();

    }
}
