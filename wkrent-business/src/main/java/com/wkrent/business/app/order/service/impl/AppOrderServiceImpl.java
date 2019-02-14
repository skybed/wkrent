package com.wkrent.business.app.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.base.obj.DataDict;
import com.wkrent.business.app.base.service.AppDataDictValueService;
import com.wkrent.business.app.order.dao.AppOrderDao;
import com.wkrent.business.app.order.obj.RentOrder;
import com.wkrent.business.app.order.service.AppOrderService;
import com.wkrent.business.app.picture.dao.AppImageDao;
import com.wkrent.business.app.rent.dao.RentRoomDao;
import com.wkrent.common.entity.BgOrder;
import com.wkrent.common.entity.BgPicAttach;
import com.wkrent.common.entity.BgRoom;

@Service
public class AppOrderServiceImpl implements AppOrderService {
	
	@Autowired
	private AppOrderDao appOrderDao;
	
	@Autowired
	private RentRoomDao rentRoomDao;
	
	@Autowired
	private AppImageDao appImageDao;
	
	@Autowired
	private AppDataDictValueService appDataDictValueService;

	@Override
	public Integer countRentOrder(String userId) {
		return appOrderDao.countRentOrder(userId);
	}

	@Override
	public List<RentOrder> getRentOrderByPager(String userId, Integer index, Integer pageSize) {

		List<RentOrder> rentOrders = new ArrayList<RentOrder>();
		List<BgOrder> bgOrders = appOrderDao.getAppOrderByPager(userId, (index - 1) * pageSize, pageSize);
		if(CollectionUtils.isNotEmpty(bgOrders)) {
			for(int i = 0; i < bgOrders.size(); i++) {
				RentOrder order = new RentOrder();
				order.setOrderId(bgOrders.get(i).getBgOrderId());
				order.setOrderNum(bgOrders.get(i).getBgOrderNumber());
				order.setOrderStatus(bgOrders.get(i).getBgOrderStatus());
				
				order.setRoomId(bgOrders.get(i).getBgOrderRoomId());
				
				BgRoom room = rentRoomDao.selectByPrimaryKey(bgOrders.get(i).getBgOrderRoomId());
				if(room != null) {
					order.setRoomName(room.getBgRoomName());
					
					String roomTips = "";
					List<DataDict> dataDicts = appDataDictValueService.queryDictValueList("房源标签");
					for(int j = 0; j < dataDicts.size(); j++) {
						String roomTipids = room.getBgRoomTips();
						if(StringUtils.isNotEmpty(roomTipids)) {
							if(roomTipids.contains(dataDicts.get(j).getDataDictId())) {
								roomTips = roomTips + dataDicts.get(j).getDataDictName() + ",";
							}
						}
					}
					
					if(roomTips.length() > 0) {
						roomTips = roomTips.substring(0, roomTips.length() - 1);
					}
					
					order.setRoomTips(roomTips);
					order.setPrice(room.getBgRoomPrice() + "/" + getPriceUnit(bgOrders.get(i).getBgOrderRentTenancy()));
				}
				
				List<BgPicAttach> picAttachs = appImageDao.selectByOwnerId(bgOrders.get(i).getBgOrderRoomId());
				if(CollectionUtils.isNotEmpty(picAttachs)) {
					order.setRoomPic(picAttachs.get(0).getPicAttachId());
				}
				rentOrders.add(order);
			}
		}
		
		return rentOrders;
	}

	private String getPriceUnit(String unit) {
		if("0".equals(unit)) {
			return "周";
		} else if("1".equals(unit)) {
			return "月";
		} else if ("2".equals(unit)) {
			return "年";
		}
		return "";
	}
	
	@Override
	public void deleteRentOrder(String orderId, String userId) {
		appOrderDao.deleteRentOrder(orderId, userId);
	}

	@Override
	public BgOrder selectByPrimaryKey(String bgOrderId) {
		return appOrderDao.selectByPrimaryKey(bgOrderId);
	}

	@Override
	public void cancelOrderAppoint(String orderId, String userId) {
		//编辑订单为已取消
		appOrderDao.cancelOrderAppoint(orderId, userId);
	}

	@Override
	public void insertOrder(BgOrder bgOrder) {
		appOrderDao.insertSelective(bgOrder);
	}

	@Override
	public BgOrder selectByRoomAndUserId(String roomId, String userId) {
		return appOrderDao.selectByRoomAndUserId(roomId, userId);
	}

}
