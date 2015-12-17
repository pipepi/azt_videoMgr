/**
 * 
 */
package com.aepan.sysmgr.model.config;

import java.io.Serializable;

/**
 * @author Administrator
 * 2015年9月24日下午4:55:40
 */
public class SmsConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_验证码 = 0;
	public static final int TYPE_非验证码 = 1;
	
	public String url;
	public String ac;
	public String authkey;
	public String cgid;
	public String csid;
	
	public String oth_url;
	public String oth_ac;
	public String oth_authkey;
	public String oth_cgid;
	public String oth_csid;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getCgid() {
		return cgid;
	}
	public void setCgid(String cgid) {
		this.cgid = cgid;
	}
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	public String getOth_url() {
		return oth_url;
	}
	public void setOth_url(String oth_url) {
		this.oth_url = oth_url;
	}
	public String getOth_ac() {
		return oth_ac;
	}
	public void setOth_ac(String oth_ac) {
		this.oth_ac = oth_ac;
	}
	public String getOth_authkey() {
		return oth_authkey;
	}
	public void setOth_authkey(String oth_authkey) {
		this.oth_authkey = oth_authkey;
	}
	public String getOth_cgid() {
		return oth_cgid;
	}
	public void setOth_cgid(String oth_cgid) {
		this.oth_cgid = oth_cgid;
	}
	public String getOth_csid() {
		return oth_csid;
	}
	public void setOth_csid(String oth_csid) {
		this.oth_csid = oth_csid;
	}

	
}
