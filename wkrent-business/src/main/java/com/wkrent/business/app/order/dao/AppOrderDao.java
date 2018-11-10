/*
*
* BgOrderMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.app.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wkrent.common.entity.BgOrder;

public interface AppOrderDao {
	
    int deleteByPrimaryKey(String bgOrderId);

    int insert(BgOrder record);

    int insertSelective(BgOrder record);

    BgOrder selectByPrimaryKey(String bgOrderId);

    int updateByPrimaryKeySelective(BgOrder record);

    int updateByPrimaryKey(BgOrder record);
    
    int countRentOrder(String userId);
    
    List<BgOrder> getAppOrderByPager(@Param("userId")String userId, @Param("sIndex")Integer sIndex, @Param("eIndex")Integer eIndex);

    void deleteRentOrder(@Param("orderId")String orderId, @Param("updateBy")String updateBy);
    
    void cancelOrderAppoint(@Param("orderId")String orderId, @Param("userId")String userId);
}