/*
*
* AppPhoneCodeHistory.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-28
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

public class AppPhoneCodeHistory implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4379080109655807642L;

	// 短信验证码发送历史id,主键
    private String phoneCodeHistoryId;

    // 手机号
    private String phone;

    // 验证码
    private String code;

    // 创建时间
    private Date createTime;

    // 是否已校验 0-否 1-是
    private String isValidate;

    // 校验时间
    private Date validateTime;

    /**
     * 
     * @return phone_code_history_id 短信验证码发送历史id,主键
     */
    public String getPhoneCodeHistoryId() {
        return phoneCodeHistoryId;
    }

    /**
     * 
     * @param phoneCodeHistoryId 短信验证码发送历史id,主键
     */
    public void setPhoneCodeHistoryId(String phoneCodeHistoryId) {
        this.phoneCodeHistoryId = phoneCodeHistoryId == null ? null : phoneCodeHistoryId.trim();
    }

    /**
     * 
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 
     * @return code 验证码
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code 验证码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return is_validate 是否已校验 0-否 1-是
     */
    public String getIsValidate() {
        return isValidate;
    }

    /**
     * 
     * @param isValidate 是否已校验 0-否 1-是
     */
    public void setIsValidate(String isValidate) {
        this.isValidate = isValidate == null ? null : isValidate.trim();
    }

    /**
     * 
     * @return validate_time 校验时间
     */
    public Date getValidateTime() {
        return validateTime;
    }

    /**
     * 
     * @param validateTime 校验时间
     */
    public void setValidateTime(Date validateTime) {
        this.validateTime = validateTime;
    }
}