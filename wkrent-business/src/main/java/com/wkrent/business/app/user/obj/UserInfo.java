package com.wkrent.business.app.user.obj;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7014132837576621707L;

	//用户Id
	private String userId;
	
	//用户名
	private String userName;
	
	//手机号
	private String phone;
	
	//图像
	private String icon;
	
	//生日
	private String birthday;
	
	//性别 0-男 1-女
	private String gender;
	
	//地区id
	private String regionId;
	
	//地区码
	private String regionCode;
	
	//邮箱
	private String email;
	
	//短信验证码
	private String msgCode;

	//验证码流水号
	private String bizId;

	public UserInfo() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
}
