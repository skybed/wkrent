/*
*
* AppFeedbackMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.AppFeedback;

public interface AppFeedbackMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String appFeedbackId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(AppFeedback record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(AppFeedback record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    AppFeedback selectByPrimaryKey(String appFeedbackId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(AppFeedback record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(AppFeedback record);
}