package com.wkrent.common.constants;

public class Constant {
	
	/**
	 * 自定义加密措施
	 */
	public static final String MD5_PWD = "WKRENT#PWD";
	
	/**
	 * 状态码 200
	 */
	public static final String RESULT_SUCCESS_CODE = "200";
	
	/**
	 * 操作成功！
	 */
	public static final String RESULT_SUCCESS_MSG = "操作成功！";
	
	/**
	 * 状态码 202
	 */
	public static final String RESULT_NOT_LOGIN_CODE = "202";
	
	/**
	 * 用户未登陆,请登陆后重试!
	 */
	public static final String RESULT_NOT_LOGIN_MSG = "用户未登陆,请登陆后重试!";
	
	/**
	 * 状态码 400
	 */
	public static final String RESULT_FAIL_CODE = "400";
	
	/**
	 * 操作失败！
	 */
	public static final String RESULT_FAIL_MSG = "操作失败！";
	
	/**
	 * 状态码 401
	 */
	public static final String RESULT_PHONE_CODE_ERROR_CODE = "401";
	
	/**
	 * 手机验证码校验失败，请重试！
	 */
	public static final String RESULT_PHONE_CODE_ERROR_MSG = "手机验证码校验失败，请重试！";

	/**
	 * 手机验证码不存在，请重试！
	 */
	public static final String RESULT_PHONE_CODE_NOT_EXIST_MSG = "手机验证码不存在，请重新输入！";

	/**
	 * 手机验证码已失效，请重新发送验证码！
	 */
	public static final String RESULT_PHONE_CODE_INVALID_MSG = "手机验证码已失效，请重新发送验证码！";

	/**
	 * 状态码 402
	 */
	public static final String RESULT_USER_EXIST_CODE = "402";
	
	/**
	 * 该手机号已被注册，注册失败！
	 */
	public static final String RESULT_USER_EXIST_MSG = "该手机号已被注册，注册失败！";
	
	/**
	 * 状态码 403
	 */
	public static final String RESULT_USER_NOT_REGISTER_CODE = "403";
	
	/**
	 * 该用户未注册，请注册后登陆！
	 */
	public static final String RESULT_USER_NOT_REGISTER_MSG = "该用户未注册，请注册后登陆！";
	
	/**
	 * 状态码 404
	 */
	public static final String RESULT_ORDER_NOT_EXISTS_CODE = "404";
	
	/**
	 * 该订单不存在，请检查后重试
	 */
	public static final String RESULT_ORDER_NOT_EXISTS_MSG = "该订单不存在，请检查后重试！";
	
	/**
	 * 状态码 405
	 */
	public static final String RESULT_ORDER_CANNOT_DELETE_CODE = "405";
	
	/**
	 * 该订单当前状态不能删除！
	 */
	public static final String RESULT_ORDER_CANNOT_DELETE_MSG = "该订单当前状态不能删除！";
	
	/**
	 * 状态码 406
	 */
	public static final String RESULT_ORDER_CANNOT_CANCEL_CODE = "406";
	
	/**
	 * 该订单当前状态不能取消预约！
	 */
	public static final String RESULT_ORDER_CANNOT_CANCEL_MSG = "该订单当前状态不能取消预约！";
	
	/**
	 * 状态码 407
	 */
	public static final String RESULT_ATTENTION_NOT_EXISTS_CODE = "407";
	
	/**
	 * 该收藏不存在，请检查后重试！
	 */
	public static final String RESULT_ATTENTION_NOT_EXISTS_MSG = "该收藏不存在，请检查后重试！";
	
	/**
	 * 状态码 408
	 */
	public static final String RESULT_RENTROOM_NOT_EXISTS_CODE = "408";
	
	/**
	 * 该房源不存在，请检查后重试！
	 */
	public static final String RESULT_RENTROOM_NOT_EXISTS_MSG = "该房源不存在，请检查后重试！";
	
	/**
	 * 状态码 300
	 */
	public static final String RESULT_REQUIRE_PARAM_CODE = "300";
	
	/**
	 * 必填项不能为空！
	 */
	public static final String RESULT_REQUIRE_PARAM_MSG = "必填项不能为空！";
	
	/**
	 * 状态码 301
	 */
	public static final String RESULT_REQUIRE_PARAM_FORMAT_CODE = "301";
	
	/**
	 * 必填项格式不正确，请检查！
	 */
	public static final String RESULT_REQUIRE_PARAM_FORMAT_MSG = "必填项格式不正确，请检查！";
	
	/**
	 * 状态码 302
	 */
	public static final String RESULT_RENTROOM_ALREADY_ATTENTION_CODE = "302";
	
	/**
	 * 该房源已收藏，请不要重复收藏！
	 */
	public static final String RESULT_RENTROOM_ALREADY_ATTENTION_MSG = "该房源已收藏，请不要重复收藏！";
	
	/**
	 * 状态码 303
	 */
	public static final String RESULT_REQUIRE_PARAM_INVALIDATE_CODE = "303";
	
	/**
	 * 必填项填写有误，请检查后重试！
	 */
	public static final String RESULT_REQUIRE_PARAM_INVALIDATE_MSG = "必填项填写有误，请检查后重试！";

	/**
	 * 状态码 304
	 */
	public static final String RESULT_RENTROOM_NOT_ATTENTION_CODE = "304";
	
	/**
	 * 该房源未被收藏，无法取消！
	 */
	public static final String RESULT_RENTROOM_NOT_ATTENTION_MSG = "该房源未被收藏，无法取消！";
	
	/**
	 * 状态码 305
	 */
	public static final String RESULT_RENTROOM_NOT_APPOINT_CODE = "305";
	
	/**
	 * 该房源已被预约，请重试！
	 */
	public static final String RESULT_RENTROOM_NOT_APPOINT_MSG = "该房源已被预约，请重试！";
	
	/**
	 * 状态码 306
	 */
	public static final String RESULT_RENTROOM_APPOINT_FULL_CODE = "306";
	
	/**
	 * 该房源已预约满，请看看其它房源
	 */
	public static final String RESULT_RENTROOM_APPOINT_FULL_MSG = "该房源已预约满，请看看其它房源！";
	
	/**
	 * 状态码 307
	 */
	public static final String RESULT_RENTROOM_APPOINT_ALREADY_CODE = "307";
	
	/**
	 * 您已预定该房源，不可重复预定
	 */
	public static final String RESULT_RENTROOM_APPOINT_ALREADY_MSG = "您已预定该房源，不可重复预定！";
}
