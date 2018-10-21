/*
*
* AppAttentionMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.AppAttention;

public interface AppAttentionMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String appAttentionId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(AppAttention record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(AppAttention record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    AppAttention selectByPrimaryKey(String appAttentionId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(AppAttention record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(AppAttention record);
}