package com.wkrent.business.app.order.obj;

public class RentOrder {
	
	//订单id，主键
	private String orderId;
	
	//订单编号
	private String orderNum;
	
	//订单状态
	private String orderStatus;
	
	//房源id
	private String roomId;
	
	//房源图片
	private String roomPic;
	
	//房源标题
	private String roomName;
	
	//价格
	private String price;
	
	//标签
	private String roomTips;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomPic() {
		return roomPic;
	}

	public void setRoomPic(String roomPic) {
		this.roomPic = roomPic;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRoomTips() {
		return roomTips;
	}

	public void setRoomTips(String roomTips) {
		this.roomTips = roomTips;
	}

}
