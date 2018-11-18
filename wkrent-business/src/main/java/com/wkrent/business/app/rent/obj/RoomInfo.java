package com.wkrent.business.app.rent.obj;

import java.io.Serializable;

public class RoomInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -555442090514461263L;
	
	//房源id，主键
	private String roomId;
	
	//房源图片
	private String roomPic;
	
	//房源标题
	private String roomName;
	
	//价格
	private String price;
	
	//状态
	private String status;
	
	//标签
	private String roomTips;
	
	//所属国家
	private String addressCountry;
	
	//所属城市
	private String addressCity;
	
	//详细地址
	private String addressDetail;
	
	//是否被收藏 0-否 1-是
	private String isAttention;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoomTips() {
		return roomTips;
	}

	public void setRoomTips(String roomTips) {
		this.roomTips = roomTips;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(String isAttention) {
		this.isAttention = isAttention;
	}
	
}
