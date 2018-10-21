package com.wkrent.common.entity.po;

import java.io.Serializable;
import java.util.Date;

public class BgUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1778772042681681344L;

	private String bgUserId;

    private String bgUserAccount;

    private String bgUserPwd;

    private String bgUserName;

    private String bgUserSex;

    private String bgUserPhone;

    private String bgUserEmail;

    private String bgUserEmpId;

    private String bgUserDept;

    private String description;

    private String isdelete;

    private String isactive;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    public String getBgUserId() {
        return bgUserId;
    }

    public void setBgUserId(String bgUserId) {
        this.bgUserId = bgUserId;
    }

    public String getBgUserAccount() {
        return bgUserAccount;
    }

    public void setBgUserAccount(String bgUserAccount) {
        this.bgUserAccount = bgUserAccount;
    }

    public String getBgUserPwd() {
        return bgUserPwd;
    }

    public void setBgUserPwd(String bgUserPwd) {
        this.bgUserPwd = bgUserPwd;
    }

    public String getBgUserName() {
        return bgUserName;
    }

    public void setBgUserName(String bgUserName) {
        this.bgUserName = bgUserName;
    }

    public String getBgUserSex() {
        return bgUserSex;
    }

    public void setBgUserSex(String bgUserSex) {
        this.bgUserSex = bgUserSex;
    }

    public String getBgUserPhone() {
        return bgUserPhone;
    }

    public void setBgUserPhone(String bgUserPhone) {
        this.bgUserPhone = bgUserPhone;
    }

    public String getBgUserEmail() {
        return bgUserEmail;
    }

    public void setBgUserEmail(String bgUserEmail) {
        this.bgUserEmail = bgUserEmail;
    }

    public String getBgUserEmpId() {
        return bgUserEmpId;
    }

    public void setBgUserEmpId(String bgUserEmpId) {
        this.bgUserEmpId = bgUserEmpId;
    }

    public String getBgUserDept() {
        return bgUserDept;
    }

    public void setBgUserDept(String bgUserDept) {
        this.bgUserDept = bgUserDept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}