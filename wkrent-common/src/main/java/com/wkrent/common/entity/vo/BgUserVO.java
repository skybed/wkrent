package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@ApiModel
public class BgUserVO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1778772042681681344L;

    /**
     * 后台用户id,唯一标识
     */
    @ApiModelProperty
    private String bgUserId;

    /**
     * 后台用户账号
     */
    private String bgUserAccount;

    /**
     * 后台用户登录密码
     */
    private String bgUserPwd;

    /**
     * 后台用户名
     */
    private String bgUserName;

    /**
     * 后台用户性别 0-男 1-女
     */
    private String bgUserSex;

    /**
     * 后台用户联系方式
     */
    private String bgUserPhone;

    /**
     * 后台用户联系邮箱
     */
    private String bgUserEmail;

    /**
     * 后台用户工号
     */
    private String bgUserEmpId;

    /**
     * 后台用户部门
     */
    private String bgUserDept;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

    /**
     * 是否启用 0-否 1-是
     */
    private String isActive;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注 预留字段
     */
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

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 性别字符串: 男/女
     */
    private String bgUserSexStr;

    /**
     * 菜单List
     */
    private List<BgMenuVO> menuList;

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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBgUserSexStr() {
        return bgUserSexStr;
    }

    public void setBgUserSexStr(String bgUserSexStr) {
        this.bgUserSexStr = bgUserSexStr;
    }

    public List<BgMenuVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<BgMenuVO> menuList) {
        this.menuList = menuList;
    }
}