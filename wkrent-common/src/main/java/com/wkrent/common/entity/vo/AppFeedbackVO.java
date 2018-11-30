package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户反馈表
 * @author skybed
 *
 */
public class AppFeedbackVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4496218679652471496L;

    /**
     * 反馈id,唯一标识
     */
    private String appFeedbackId;

    /**
     * 反馈标题
     */
    private String appFeedbackTitle;

    /**
     * 反馈内容
     */
    private String appFeedbackContent;

    /**
     * 反馈人联系方式
     */
    private String appFeedbackContact;

    /**
     * 反馈时间
     */
    private Date appFeedbackTime;

    /**
     * 反馈状态 0-未处理 1-处理中 2-已处理
     */
    private String appFeedbackStatus;

    /**
     * 答复内容
     */
    private String appFeedbackReply;

    /**
     * 答复时间
     */
    private Date appFeedbackReplyTime;

    /**
     * 描述
     */
    private String description;

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
     * 最后更新人姓名
     */
    private String updateName;

    /**
     * 备注 预留字段
     */
    private String remark;

    /**
     * 用户反馈IdList
     */
    private List<String> feedBackIdList;

    /**
     * 附件idList
     */
    private List<String> fileIdList;

    /**
     * 分页信息
     */
    private Page page;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

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

    /**
     * 
     * @return app_feedback_id 反馈id,唯一标识
     */
    public String getAppFeedbackId() {
        return appFeedbackId;
    }

    /**
     * 
     * @param appFeedbackId 反馈id,唯一标识
     */
    public void setAppFeedbackId(String appFeedbackId) {
        this.appFeedbackId = appFeedbackId == null ? null : appFeedbackId.trim();
    }

    /**
     * 
     * @return app_feedback_title 反馈标题
     */
    public String getAppFeedbackTitle() {
        return appFeedbackTitle;
    }

    /**
     * 
     * @param appFeedbackTitle 反馈标题
     */
    public void setAppFeedbackTitle(String appFeedbackTitle) {
        this.appFeedbackTitle = appFeedbackTitle == null ? null : appFeedbackTitle.trim();
    }

    /**
     * 
     * @return app_feedback_content 反馈内容
     */
    public String getAppFeedbackContent() {
        return appFeedbackContent;
    }

    /**
     * 
     * @param appFeedbackContent 反馈内容
     */
    public void setAppFeedbackContent(String appFeedbackContent) {
        this.appFeedbackContent = appFeedbackContent == null ? null : appFeedbackContent.trim();
    }

    /**
     * 
     * @return app_feedback_contact 反馈人联系方式
     */
    public String getAppFeedbackContact() {
        return appFeedbackContact;
    }

    /**
     * 
     * @param appFeedbackContact 反馈人联系方式
     */
    public void setAppFeedbackContact(String appFeedbackContact) {
        this.appFeedbackContact = appFeedbackContact == null ? null : appFeedbackContact.trim();
    }

    /**
     * 
     * @return app_feedback_time 反馈时间
     */
    public Date getAppFeedbackTime() {
        return appFeedbackTime;
    }

    /**
     * 
     * @param appFeedbackTime 反馈时间
     */
    public void setAppFeedbackTime(Date appFeedbackTime) {
        this.appFeedbackTime = appFeedbackTime;
    }

    /**
     * 
     * @return app_feedback_status 反馈状态 0-未处理 1-处理中 2-已处理
     */
    public String getAppFeedbackStatus() {
        return appFeedbackStatus;
    }

    /**
     * 
     * @param appFeedbackStatus 反馈状态 0-未处理 1-处理中 2-已处理
     */
    public void setAppFeedbackStatus(String appFeedbackStatus) {
        this.appFeedbackStatus = appFeedbackStatus == null ? null : appFeedbackStatus.trim();
    }

    /**
     * 
     * @return app_feedback_reply 答复内容
     */
    public String getAppFeedbackReply() {
        return appFeedbackReply;
    }

    /**
     * 
     * @param appFeedbackReply 答复内容
     */
    public void setAppFeedbackReply(String appFeedbackReply) {
        this.appFeedbackReply = appFeedbackReply == null ? null : appFeedbackReply.trim();
    }

    /**
     * 
     * @return app_feedback_reply_time 答复时间
     */
    public Date getAppFeedbackReplyTime() {
        return appFeedbackReplyTime;
    }

    /**
     * 
     * @param appFeedbackReplyTime 答复时间
     */
    public void setAppFeedbackReplyTime(Date appFeedbackReplyTime) {
        this.appFeedbackReplyTime = appFeedbackReplyTime;
    }

    /**
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 
     * @return create_by 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
     * @return update_by 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * 
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return remark 备注 预留字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 备注 预留字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public List<String> getFeedBackIdList() {
        return feedBackIdList;
    }

    public void setFeedBackIdList(List<String> feedBackIdList) {
        this.feedBackIdList = feedBackIdList;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<String> getFileIdList() {
        return fileIdList;
    }

    public void setFileIdList(List<String> fileIdList) {
        this.fileIdList = fileIdList;
    }
}