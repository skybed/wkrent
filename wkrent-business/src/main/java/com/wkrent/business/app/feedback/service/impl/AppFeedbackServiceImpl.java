package com.wkrent.business.app.feedback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.feedback.dao.AppFeedbackDao;
import com.wkrent.business.app.feedback.service.AppFeedbackService;
import com.wkrent.common.entity.AppFeedback;

@Service
public class AppFeedbackServiceImpl implements AppFeedbackService {
	
	@Autowired
	private AppFeedbackDao appFeedbackDao;

	@Override
	public void insertFeedbackInfo(AppFeedback feedback) {
		appFeedbackDao.insertSelective(feedback);
	}

}
