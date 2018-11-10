package com.wkrent.business.app.rent.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.rent.dao.AppAttentionDao;
import com.wkrent.business.app.rent.service.AppAttentionService;
import com.wkrent.common.entity.AppAttention;

@Service
public class AppAttentionServiceImpl implements AppAttentionService {
	
	@Autowired
	private AppAttentionDao appAttentionDao;

	@Override
	public List<AppAttention> getAppAttentionByPager(String userId, Integer index, Integer pageSize) {
		return appAttentionDao.getAppAttentionByPager((index - 1) * pageSize, index * pageSize, userId);
	}

	@Override
	public Integer countAppAttention(String userId) {
		return appAttentionDao.countAppAttention(userId);
	}

	@Override
	public void deleteAppAttention(String appAttentionId, String userId) {
		AppAttention appAttention = new AppAttention();
		appAttention.setAppAttentionId(appAttentionId);
		appAttention.setIsDelete("1");
		appAttention.setUpdateBy(userId);
		appAttention.setUpdateTime(new Date());
		appAttentionDao.updateByPrimaryKeySelective(appAttention);
	}

	@Override
	public void saveAppAttention(AppAttention record) {
		appAttentionDao.insert(record);
	}

	@Override
	public AppAttention selectByRoomAndUserId(String roomId, String userId) {
		return appAttentionDao.selectByRoomAndUserId(roomId, userId);
	}

	@Override
	public AppAttention selectById(String attentionId) {
		return appAttentionDao.selectByPrimaryKey(attentionId);
	}

}
