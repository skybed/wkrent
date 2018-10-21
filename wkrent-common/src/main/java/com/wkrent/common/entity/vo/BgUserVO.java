package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
public class BgUserVO implements Serializable {
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

    private String isactive;

    private String createBy;

    private Date createTime;

    private String remark;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 分页信息
     */
    private Page page;

    /**
     * 角色id
     */
    private String roleId;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}