package com.wkrent.business.app.rent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.rent.dao.AppAttentionDao;
import com.wkrent.business.app.rent.obj.RoomInfo;
import com.wkrent.business.app.rent.service.AppAttentionService;
import com.wkrent.common.entity.AppAttention;
import com.wkrent.common.entity.BgRoom;

@Service
public class AppAttentionServiceImpl implements AppAttentionService {
	
	@Autowired
	private AppAttentionDao appAttentionDao;

	@Override
	public List<RoomInfo> getAppAttentionByPager(String userId, Integer index, Integer pageSize) {
		List<RoomInfo> infos = new ArrayList<RoomInfo>();
		List<BgRoom> roomInfos = appAttentionDao.getAppAttentionByPager((index - 1) * pageSize, pageSize, userId);
		if(roomInfos != null && roomInfos.size() > 0) {
			for(int i = 0; i < roomInfos.size(); i++) {
				RoomInfo roomInfo = new RoomInfo();
				roomInfo.setRoomId(roomInfos.get(i).getBgRoomId());
				roomInfo.setRoomName(roomInfos.get(i).getBgRoomName());
				roomInfo.setAddressCountry(roomInfos.get(i).getBgRoomAddressCountry());
				roomInfo.setAddressCity(roomInfos.get(i).getBgRoomAddressCity());
				roomInfo.setAddressDetail(roomInfos.get(i).getBgRoomAddressDetail());
				roomInfo.setPrice(roomInfos.get(i).getBgRoomPrice() + "/" + getPriceUnit(roomInfos.get(i).getBgRoomPriceUnit()));
				roomInfo.setStatus(roomInfos.get(i).getBgRoomStatus());
				roomInfo.setRoomTips(roomInfos.get(i).getBgRoomTips());
				roomInfo.setAddressCountry(roomInfos.get(i).getBgRoomAddressCountry());
				roomInfo.setAddressCity(roomInfos.get(i).getBgRoomAddressCity());
				roomInfo.setAddressDetail(roomInfos.get(i).getBgRoomAddressDetail());
				roomInfo.setIsAttention("1");
			
				infos.add(roomInfo);
			}
		}
		return infos;
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
	
	public String getPriceUnit(String unit) {
		if("0".equals(unit)) {
			return "周";
		} else if("1".equals(unit)) {
			return "月";
		} else if ("2".equals(unit)) {
			return "年";
		}
		return "";
	}

}
