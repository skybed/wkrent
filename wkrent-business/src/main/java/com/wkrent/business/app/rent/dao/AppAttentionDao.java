/*
*
* AppAttentionMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.app.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wkrent.common.entity.AppAttention;

public interface AppAttentionDao {
	
    int deleteByPrimaryKey(String appAttentionId);

    int insert(AppAttention record);

    int insertSelective(AppAttention record);

    AppAttention selectByPrimaryKey(String appAttentionId);

    int updateByPrimaryKeySelective(AppAttention record);

    int updateByPrimaryKey(AppAttention record);
    
	List<AppAttention> getAppAttentionByPager(@Param("sIndex")Integer sIndex, @Param("eIndex")Integer eIndex, @Param("userId")String userId);
    
	Integer countAppAttention(String userId);
	
	AppAttention selectByRoomAndUserId(@Param("roomId")String roomId, @Param("userId")String userId);
}