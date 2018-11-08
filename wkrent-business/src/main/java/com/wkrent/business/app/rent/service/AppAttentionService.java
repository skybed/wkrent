package com.wkrent.business.app.rent.service;

import java.util.List;

import com.wkrent.common.entity.AppAttention;

public interface AppAttentionService {
	
	public List<AppAttention> getAppAttentionByPager(String userId, Integer index, Integer pageSize);
	
	public Integer countAppAttention(String userId);
	
	public void deleteAppAttention(String appAttentionId, String userId);
	
	public void saveAppAttention(AppAttention record);
	
	public AppAttention selectByRoomAndUserId(String roomId, String userId);
	
	public AppAttention selectById(String attentionId);
}
