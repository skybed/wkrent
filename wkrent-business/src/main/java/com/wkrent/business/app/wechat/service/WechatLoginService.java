package com.wkrent.business.app.wechat.service;

import java.util.Map;

import com.wkrent.business.app.wechat.obj.AccessToken;
import com.wkrent.business.app.wechat.obj.WechatUserUnionID;

public interface WechatLoginService {
	
	Map<String,String> wechatLoginUrl();
	
	AccessToken getAccessToken(String code);
	
	WechatUserUnionID getUserUnionID();

}
