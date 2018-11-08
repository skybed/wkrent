/*
*
* AppFeedbackMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.app.feedback.dao;

import com.wkrent.common.entity.AppFeedback;

public interface AppFeedbackDao {
	
    int deleteByPrimaryKey(String appFeedbackId);

    int insert(AppFeedback record);

    int insertSelective(AppFeedback record);

    AppFeedback selectByPrimaryKey(String appFeedbackId);

    int updateByPrimaryKeySelective(AppFeedback record);

    int updateByPrimaryKey(AppFeedback record);
}