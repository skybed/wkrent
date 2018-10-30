package com.wkrent.business.app.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.wkrent.business.app.picture.service.AppImageService;
import com.wkrent.business.app.user.obj.UserInfo;
import com.wkrent.business.app.user.service.AppPhoneCodeHistoryService;
import com.wkrent.business.app.user.service.AppUserService;
import com.wkrent.business.app.util.AliSmsUtil;
import com.wkrent.business.app.util.RandomNumUtil;
import com.wkrent.business.app.util.UUIDUtil;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.AppPhoneCodeHistory;
import com.wkrent.common.entity.AppUser;
import com.wkrent.common.entity.BgPicAttach;
import com.wkrent.common.obj.ResultData;

@Controller
@RequestMapping("/app/api")
public class UserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AppPhoneCodeHistoryService appPhoneCodeHistoryService;
	
	@Autowired
	private AppImageService appImageService;
	
	/**
	 * 获取验证码
	 * @param request
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/sendAuthCode.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendAuthCode(HttpServletRequest request, String phone) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		map.put("bizId", "");
		
		if(StringUtils.isNotEmpty(phone)) {
			//调用阿里api发送短信验证码
			try {
				String code = RandomNumUtil.getRandomNum(4);
				SendSmsResponse response = AliSmsUtil.sendSms(phone, code);
				if(response.getCode() != null && response.getCode().equals("OK")) {
					
					AppPhoneCodeHistory codeHistory = new AppPhoneCodeHistory();
					codeHistory.setPhoneCodeHistoryId(UUIDUtil.getUUIDString());
					codeHistory.setPhone(phone);
					codeHistory.setCode(code);
					codeHistory.setCreateTime(new Date());
					codeHistory.setIsValidate("0");
					appPhoneCodeHistoryService.insertCodeHistory(codeHistory);
					
					//返回处理编号
					map.put("bizId", codeHistory.getPhoneCodeHistoryId());
					map.put("flag", true);
				}
			} catch (ClientException e) {
				resultData.setCode(Constant.RESULT_FAIL_CODE);
				resultData.setMsg(Constant.RESULT_FAIL_MSG);
				Logger.getRootLogger().error("发送短信验证码抛异常，异常信息为：" + e.getMessage(), e);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 判断验证码是否正确
	 * @param phone
	 * @param code
	 * @param bizId
	 * @return
	 */
	private boolean validateAuthCode(String phone, String code, String bizId) {
		//返回
		boolean flag = false;
		
		//不为空
		if(StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(bizId) && StringUtils.isNotEmpty(code)) {
			AppPhoneCodeHistory codeHistory = appPhoneCodeHistoryService.getPhoneCodeById(bizId);
			
			//存在 手机号一致 未被校验
			if(codeHistory != null && codeHistory.getPhone().equals(phone) 
					&& "0".equals(codeHistory.getIsValidate()) && code.equals(codeHistory.getCode())) {
				flag = true;
				
				//修改验证码已被验证
				appPhoneCodeHistoryService.validatePhoeCode(codeHistory.getPhoneCodeHistoryId());
			}
		}
		return flag;
	}
	
	/**
	 * 判断手机号是否已被注册
	 * @param request
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/checkPhoneAvailable.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkPhoneAvailable(HttpServletRequest request, String phone) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", true);
		
		//必填项不能为空
		if(StringUtils.isNotEmpty(phone)) {
			AppUser user = appUserService.getUserByPhone(phone);
			if(user != null) {
				resultData.setCode(Constant.RESULT_USER_EXIST_CODE);
				resultData.setMsg(Constant.RESULT_USER_EXIST_MSG);
				
				map.put("flag", false);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
			
			map.put("flag", false);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}

	/**
	 * 用户注册
	 * @param request
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/userRegister.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userRegister(HttpServletRequest request, UserInfo userInfo) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		map.put("userId", "");
		
		//必填项不能为空
		if(userInfo != null && StringUtils.isNotEmpty(userInfo.getEmail()) && StringUtils.isNotEmpty(userInfo.getPhone()) 
				&& StringUtils.isNotEmpty(userInfo.getMsgCode()) &&StringUtils.isNotEmpty(userInfo.getBizId())) {
			//判断邮箱是否正确
			if(checkEmail(userInfo.getEmail())) {
				//判断短信验证码是否正确
				if(validateAuthCode(userInfo.getPhone(), userInfo.getMsgCode(), userInfo.getBizId())) {
					//判断用户手机号是存在
					AppUser user = appUserService.getUserByPhone(userInfo.getPhone());
					if(user != null) {
						resultData.setCode(Constant.RESULT_USER_EXIST_CODE);
						resultData.setMsg(Constant.RESULT_USER_EXIST_MSG);
					} else {
						user = new AppUser();
						user.setAppUserId(UUIDUtil.getUUIDString());
						user.setAppUserEmail(userInfo.getEmail());
						//生成用户编号
						user.setAppUserNumber(RandomNumUtil.getRandomNum());
						user.setAppUserPhone(userInfo.getPhone());
						user.setIsDelete("0");
						user.setCreateBy("register");
						user.setCreateTime(new Date());
						
						//注册用户
						appUserService.insertAppUser(user);
						
						map.put("flag", true);
						map.put("userId", user.getAppUserId());
					}
				} else {//验证码不正确
					resultData.setCode(Constant.RESULT_PHONE_CODE_ERROR_CODE);
					resultData.setMsg(Constant.RESULT_PHONE_CODE_ERROR_MSG);
				}
			} else {//格式不正确
				resultData.setCode(Constant.RESULT_REQUIRE_PARAM_FORMAT_CODE);
				resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_FORMAT_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 验证邮箱
	 *
	 * @param email
	 * @return
	 */
	private static boolean checkEmail(String email) {
	    boolean flag = false;
	    try {
	        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	        Pattern regex = Pattern.compile(check);
	        Matcher matcher = regex.matcher(email);
	        flag = matcher.matches();
	    } catch (Exception e) {
	    	Logger.getRootLogger().error("邮箱校验抛异常，异常信息为：" + e.getMessage(), e);
	        flag = false;
	    }
	    return flag;
	}
	
	/**
	 * 用户登陆
	 * @param request
	 * @param phone
	 * @param code
	 * @param bizId
	 * @return
	 */
	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userLogin(HttpServletRequest request, String phone, String code, String bizId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		map.put("userId", "");
		
		//必填项不能为空
		if(StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(bizId)) {
			//判断短信验证码是否正确
			if(validateAuthCode(phone, code, bizId)) {
				AppUser user = appUserService.getUserByPhone(phone);
				if(user != null) {//用户存在
					map.put("flag", true);
					map.put("userId", user.getAppUserId());
					
					//将用户信息写缓存
					request.getSession().setAttribute("userId", user.getAppUserId());
					request.getSession().setAttribute("phone", phone);
				} else {//用户未注册
					resultData.setCode(Constant.RESULT_USER_NOT_REGISTER_CODE);
					resultData.setMsg(Constant.RESULT_USER_NOT_REGISTER_MSG);
				}
			} else {//验证码不正确
				resultData.setCode(Constant.RESULT_PHONE_CODE_ERROR_CODE);
				resultData.setMsg(Constant.RESULT_PHONE_CODE_ERROR_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 获取用户详情信息
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getUserDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getUserDetail(HttpServletRequest request, String userId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//必填项不能为空
		if(StringUtils.isNotEmpty(userId)) {
			AppUser user = appUserService.getUserById(userId);
			if(user != null) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(user.getAppUserId());
				userInfo.setPhone(user.getAppUserPhone());
				userInfo.setBirthday(user.getAppUserBirthday());
				userInfo.setGender(user.getAppUserSex());
				userInfo.setUserName(user.getAppUserName());
				
				//获取用户头像
				List<BgPicAttach> picAttachs = appImageService.selectByOwnerId(user.getAppUserId());
				if(picAttachs != null && picAttachs.size() > 0) {
					userInfo.setIcon(picAttachs.get(0).getPicAttachUrl());
				}
				
				resultData.setData(JSON.toJSONString(userInfo));
			} else {
				resultData.setCode(Constant.RESULT_USER_NOT_REGISTER_CODE);
				resultData.setMsg(Constant.RESULT_USER_NOT_REGISTER_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 修改用户基本信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editUserBaseInfo.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editUserBaseInfo(HttpServletRequest request, UserInfo userInfo) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//必填项不能为空
		if(StringUtils.isNotEmpty(userInfo.getUserId())) {
			//判断用户id是否存在
			AppUser user = appUserService.getUserById(userInfo.getUserId());
			if(user != null) {
				if(StringUtils.isNotEmpty(userInfo.getBirthday())) {
					user.setAppUserBirthday(userInfo.getBirthday());
				}
				
				if(StringUtils.isNotEmpty(userInfo.getUserName())) {
					user.setAppUserName(userInfo.getUserName());
				}
				
				if(StringUtils.isNotEmpty(userInfo.getGender())) {
					user.setAppUserSex(userInfo.getGender());
				}
				appUserService.updateUser(user);
				
				resultData.setData("");
			} else {
				resultData.setCode(Constant.RESULT_USER_NOT_REGISTER_CODE);
				resultData.setMsg(Constant.RESULT_USER_NOT_REGISTER_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 修改手机号
	 * @param request
	 * @param userId
	 * @param oldPhone
	 * @param newPhone
	 * @param code
	 * @param bizId
	 * @return
	 */
	@RequestMapping(value = "/editUserPhone.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editUserPhone(HttpServletRequest request, String userId, String oldPhone, String newPhone, String code, String bizId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//必填项不能为空
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(oldPhone) && StringUtils.isNotEmpty(newPhone) 
				&& StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(bizId)) {
			//判断用户id是否存在
			AppUser user = appUserService.getUserById(userId);
			if(user != null && oldPhone.equals(user.getAppUserPhone())) {
				boolean flag = validateAuthCode(oldPhone, code, bizId);
				if(flag) {//校验通过
					user.setAppUserPhone(newPhone);
					appUserService.updateUser(user);
				} else {
					resultData.setCode(Constant.RESULT_PHONE_CODE_ERROR_CODE);
					resultData.setMsg(Constant.RESULT_PHONE_CODE_ERROR_MSG);
				}
				
				resultData.setData("");
			} else {
				resultData.setCode(Constant.RESULT_USER_NOT_REGISTER_CODE);
				resultData.setMsg(Constant.RESULT_USER_NOT_REGISTER_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userLoginOut.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userLoginOut(HttpServletRequest request) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		resultData.setData("");
		
		//将用户信息缓存移除
		request.getSession().removeAttribute("userId");
		request.getSession().removeAttribute("phone");
		
		return JSON.toJSONString(resultData);
	}
	
}
