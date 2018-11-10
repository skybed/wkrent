package com.wkrent.business.app.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wkrent.business.app.wechat.obj.AccessToken;
import com.wkrent.business.app.wechat.obj.WechatUserUnionID;
import com.wkrent.business.app.wechat.service.WechatLoginService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.util.AesUtil;
import com.wkrent.common.util.DateUtils;

@Controller
@RequestMapping("/wechat")
public class WechatLoginController {

	@Autowired
	private WechatLoginService wechatLoginService;

	/**
	 * Title: list
	 * Description: 重定向到微信扫码登录二维码页面
	  * 此处显示要微信要扫描的二维码
	 * 
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@GetMapping(value = "/list")
	public String list(Model model) throws UnsupportedEncodingException {
		Map<String, String> wechatLoginUrl = wechatLoginService.wechatLoginUrl();
		String url = wechatLoginUrl.get("url");
		return "redirect:" + url;
	}

	/**
	 * Title: callback
	 * Description: 回调地址处理
	 * 
	 * @param code
	 * @param state
	 * @return
	 * @return
	 */
	@SuppressWarnings("unused")
	@GetMapping(value = "/callback")
	public String callback(String code, String state) {
		if (code != null && state != null) {
			// 验证state为了用于防止跨站请求伪造攻击
			String decrypt = AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
			if (!decrypt.equals(Constant.MD5_PWD + DateUtils.getYYYYMMdd())) {
				return "redirect:/security/login";
			}
			AccessToken access = wechatLoginService.getAccessToken(code);
			if (access != null) {
				// 把获取到的OPENID和ACCESS_TOKEN写到Properties文件中
				// 根据OPENID去当前用户数据库进行查询是否存在,此处根据自己的业务自己斟酌
				// 我只是给个例子
				if (false) {		/*不存在*/
					return "redirect:/wechat/bind";
				} else {
					// 存在则把当前账号信息授权给扫码用户
					// 拿到openid获取微信用户的基本信息
					WechatUserUnionID userUnionID = wechatLoginService.getUserUnionID();
					return "主页";
				}
			}
		}
		return null;
	}

	/**
	 * Title: bindingUserMac
	 * Description: 跳转到绑定用户系统帐号页面
	 * @return
	 */
	@GetMapping(value = "/bind")
	public String bindUserMac() {
		return "sysSetting/wxbinding";
	}
}
