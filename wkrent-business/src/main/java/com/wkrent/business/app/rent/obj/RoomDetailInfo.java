package com.wkrent.business.app.rent.obj;

import java.util.List;

public class RoomDetailInfo {
	
	//房源id，主键
	private String roomId;
	
	//房源图片列表
	private List<String> pics;
	
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

	/**
	 * 所属地区（省份）
	 */
	private String addressProvince;
	
	//详细地址
	private String addressDetail;
	
	//经度
	private String addressX;
	
	//维度
	private String addressY;

	//房屋详情
	private String roomDetail;
	
	//是否被收藏 0-否 1-是
	private String isAttention;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
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

	public String getAddressX() {
		return addressX;
	}

	public void setAddressX(String addressX) {
		this.addressX = addressX;
	}

	public String getAddressY() {
		return addressY;
	}

	public void setAddressY(String addressY) {
		this.addressY = addressY;
	}

	public String getRoomDetail() {
		return roomDetail;
	}

	public void setRoomDetail(String roomDetail) {
		this.roomDetail = roomDetail;
	}

	public String getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(String isAttention) {
		this.isAttention = isAttention;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
}
