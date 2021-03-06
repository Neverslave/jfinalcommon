package com.henry.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLoginlog<M extends BaseLoginlog<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setAccountId(java.lang.String accountId) {
		set("accountId", accountId);
	}
	
	public java.lang.String getAccountId() {
		return getStr("accountId");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}
	
	public java.lang.String getIp() {
		return getStr("ip");
	}

	public void setLoginTime(java.util.Date loginTime) {
		set("loginTime", loginTime);
	}
	
	public java.util.Date getLoginTime() {
		return get("loginTime");
	}

}
