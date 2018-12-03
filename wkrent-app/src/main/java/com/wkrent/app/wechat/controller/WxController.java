package com.wkrent.app.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wkrent.common.util.PropertiesUtils;

@Controller
@RequestMapping("/wx")
public class WxController {

	private static String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	private static String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	private static String openUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	
    private static Logger log = Logger.getLogger(WxController.class);
     
   /**
	* 向指定URL发送GET方法的请求
	* 
	* @param url
	*            发送请求的URL
	* @param param
	*            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	* @return URL 所代表远程资源的响应结果
	* 
	* 用户同意授权，获取code
	*/
    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public void authorize(HttpServletRequest request, HttpServletResponse response) {
    	String appid = PropertiesUtils.getProperty("wechat.appid");
        String uri = urlEncodeUTF8(PropertiesUtils.getProperty("wechat.redirect.url"));
        try {
            String url = authUrl.replace("APPID", appid);
            url = url.replace("URI", uri);
            
            response.sendRedirect(url);
        } catch (Exception e) {
        	log.error("authorize出现异常！" + e.getMessage(), e);
        }
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/weixinLogin", method = RequestMethod.GET)
    @ResponseBody
    public void weixinLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 用户同意授权后，能获取到code
        Map<String, String[]> params = request.getParameterMap();//针对get获取get参数
        String[] codes = params.get("code");//拿到code的值
        String code = codes[0];//code
        
        // 用户同意授权
        if (!"authdeny".equals(code)) {
        	String appid = PropertiesUtils.getProperty("wechat.appid");
        	String appSecret = PropertiesUtils.getProperty("wechat.appsecret");
        	
        	// 获取网页授权access_token
            Oauth2Token oauth2Token = getOauth2AccessToken(appid, appSecret, code);
            
            // 网页授权接口访问凭证
            String accessToken = oauth2Token.getAccessToken();
            
            // 用户标识
            String openId = oauth2Token.getOpenId();
            
            // 获取用户信息
            SNSUserInfo snsUserInfo = getSNSUserInfo(accessToken, openId);
            
            log.info("**用户信息unionId：" + snsUserInfo.getUnionid() + "***:" + snsUserInfo.getNickname());
            
            // 设置要传递的参数
            
            //具体业务start

            //具体业务end

            return ;
        }
    }  

    
   /**
	* 获取网页授权凭证
	* 
	* @param appId 公众账号的唯一标识
	* @param appSecret 公众账号的密钥
	* @param code
	* @return Oauth2Token
	*/
    public static Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        Oauth2Token wat = null;
        
        // 拼接请求地址
        String requestUrl = tokenUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        
        // 获取网页授权凭证
        JSONObject jsonObject = JSON.parseObject(WxHttpUtil.get(requestUrl));
        if (null != jsonObject) {
            try {
                wat = new Oauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInteger("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode: " + errorCode + " errmsg: " + errorMsg, e);
            }
        }
        return wat;
    }
    
   /**
	* 通过网页授权获取用户信息
	* 
	* @param accessToken 网页授权接口调用凭证
	* @param openId 用户标识
	* @return SNSUserInfo
	*/
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        
        // 拼接请求地址
        String requestUrl = openUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        
        // 通过网页授权获取用户信息
        JSONObject jsonObject =  JSON.parseObject(WxHttpUtil.get(requestUrl));

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInteger("sex"));
                
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                
                // 用户特权信息
                List<String> list = JSON.parseArray(jsonObject.getString("privilege"),String.class);
                snsUserInfo.setPrivilegeList(list);
                
                //与开放平台共用的唯一标识，只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
                snsUserInfo.setUnionid(jsonObject.getString("unionid"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode: " + errorCode + " errmsg: " + errorMsg, e);
            }
        }
        return snsUserInfo;
    }
   
    /**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}