package com.wkrent.app.wechat.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wkrent.app.util.RandomNumUtil;
import com.wkrent.app.util.UUIDUtil;
import com.wkrent.business.app.picture.service.AppImageService;
import com.wkrent.business.app.user.service.AppUserService;
import com.wkrent.common.entity.AppUser;
import com.wkrent.common.entity.BgPicAttach;
import com.wkrent.common.util.PropertiesUtils;

@Controller
@RequestMapping("/wx")
public class WxController {
	
	//图片存放地址
	private static final String UPLOAD_DIRECTORY = PropertiesUtils.getProperty("fileupload.directory", "/wkrent/app/upload/image");
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AppImageService appImageService;

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
    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
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
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
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
            
            if(StringUtils.isNotEmpty(openId)) {
            	 //判断openId是否存在
                AppUser user = appUserService.getUserByOpenId(openId);
                if(user == null) {
                	// 获取用户信息
                    SNSUserInfo snsUserInfo = getSNSUserInfo(accessToken, openId);
                    
                    log.info("**用户信息：" + JSON.toJSONString(snsUserInfo));
                    
                    //注册新用户
                    user = new AppUser();
                    user.setAppUserId(UUIDUtil.getUUIDString());
					//生成用户编号
					user.setAppUserNumber(RandomNumUtil.getRandomNum());
					user.setIsDelete("0");
					user.setCreateBy("register");
					user.setCreateTime(new Date());
					user.setAppUserCity(snsUserInfo.getCity());
					user.setAppUserCountry(snsUserInfo.getCountry());
					user.setAppUserName(snsUserInfo.getNickname());
					user.setAppUserWechatOpenid(openId);
					
					if(1 == snsUserInfo.getSex()) {
						user.setAppUserSex("0");
					} else if(2 == snsUserInfo.getSex()) {
						user.setAppUserSex("1");
					} else {
						user.setAppUserSex("2");
					}
					appUserService.insertAppUser(user);
					
					String headUrl = snsUserInfo.getHeadImgUrl();
					downloadPicture(headUrl, user.getAppUserId());
                }
                request.getSession().setAttribute("current_user_open_id", openId);
                
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/guoke/#!/login";
                //跳转到注册页
                response.sendRedirect(basePath);
            }
        }
    }
    
	/**链接url下载图片
	 * 
	 * @param urlList
	 */
    private void downloadPicture(String urlList, String ownerId) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            
            //修改文件名称 uuid
            String fileUUID = UUIDUtil.getUUIDString();
            
            //获取后缀
            String prefix = "png";
            
            //修改后完整的文件名称
            String nFileName = fileUUID + "." + prefix;
            
            // 文件保存路径
            String filePath = FilenameUtils.concat(UPLOAD_DIRECTORY, nFileName);
            
            //判断文件目录是否存在，否则自动生成
            File directory = new File(UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            
            //取消该用户之前的头像
            appImageService.deletePicAttachByOwnerId(ownerId);
            
            //写附件表
            BgPicAttach picAttach = new BgPicAttach();
            picAttach.setPicAttachId(fileUUID);
            picAttach.setPicAttachName(nFileName);
            picAttach.setIsDelete("0");
            picAttach.setPicAttachFileType(prefix);
            picAttach.setCreateTime(new Date());
            picAttach.setUpdateTime(new Date());
            picAttach.setPicAttachUrl(filePath);
            picAttach.setPicAttachOwner(ownerId);
            appImageService.savePicAttach(picAttach);
        } catch (Exception e) {
            log.error("保持图片异常，异常信息为:" + e.getMessage(), e);
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