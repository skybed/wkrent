package com.wkrent.business.app.order.service;

import java.util.List;

import com.wkrent.business.app.order.obj.RentOrder;
import com.wkrent.common.entity.BgOrder;

public interface AppOrderService {

	public Integer countRentOrder(String userId);
	
	public List<RentOrder> getRentOrderByPager(String userId, Integer index, Integer pageSize);

	public void deleteRentOrder(String orderId, String userId);
	
	public BgOrder selectByPrimaryKey(String bgOrderId);
	
	public void cancelOrderAppoint(String orderId, String userId);
	
	public void insertOrder(BgOrder bgOrder);
}
