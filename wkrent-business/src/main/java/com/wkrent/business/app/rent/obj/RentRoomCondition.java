package com.wkrent.business.app.rent.obj;

import java.io.Serializable;

public class RentRoomCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4224415550046727195L;
	
	//用户Id
	private String userId;

	//是否推荐 0-不推荐 1-推荐
	private String recommend;
	
	//价格排序 0-升序 1-降序
	private String priceSort;
	
	//时间排序 0-升序 1-降序
	private String timeSort;
	
	//房屋类型
	private String roomType;
	
	//最低价
	private Double minPrice;
	
	//最高价
	private Double maxPrice;
	
	//当前页
	private Integer currentIndex;
	
	//页大小
	private Integer pageSize;
	
	//开始索引
	private Integer startIndex;
	
	//结束索引
	private Integer endIndex;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(String priceSort) {
		this.priceSort = priceSort;
	}

	public String getTimeSort() {
		return timeSort;
	}

	public void setTimeSort(String timeSort) {
		this.timeSort = timeSort;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
}
