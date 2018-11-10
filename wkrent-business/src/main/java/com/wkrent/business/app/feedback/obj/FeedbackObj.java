package com.wkrent.business.app.feedback.obj;

import java.util.List;

public class FeedbackObj {
	
	//用户Id
	private String userId;
	
	//反馈标题
	private String feedbackTitle;
	
	//反馈内容
	private String feedbackContent;
	
	//反馈图片
	private List<String> feedbackPics;
	
	//联系方式
	private String phone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFeedbackTitle() {
		return feedbackTitle;
	}

	public void setFeedbackTitle(String feedbackTitle) {
		this.feedbackTitle = feedbackTitle;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public List<String> getFeedbackPics() {
		return feedbackPics;
	}

	public void setFeedbackPics(List<String> feedbackPics) {
		this.feedbackPics = feedbackPics;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
