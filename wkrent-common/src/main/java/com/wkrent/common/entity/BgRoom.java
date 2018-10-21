/*
*
* BgRoom.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表
 * @author skybed
 *
 */
public class BgRoom implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1662427978475676801L;

	// 后台房源id,唯一标识
    private String bgRoomId;

    // 房源编号
    private String bgRoomNumber;

    // 房源名称
    private String bgRoomName;

    // 所属国籍
    private String bgRoomAddressCountry;

    // 所属城市
    private String bgRoomAddressCity;

    // 详细地址
    private String bgRoomAddressDetail;

    // 经度
    private Double bgRoomAddressX;

    // 纬度
    private Double bgRoomAddressY;

    // 价格
    private Double bgRoomPrice;

    // 单位 0-周 1-月 2-年
    private String bgRoomPriceUnit;

    // 房源状态 1-出租中 2-已被预订 3-房源锁定 4-待签租房合同 5-已出租 6-已下架
    private String bgRoomStatus;

    // 最大预约数
    private Byte bgRoomMaxAppointNum;

    // 是否推荐房源 0-否 1-是
    private String bgRoomRecommend;

    // 房源标签 多个英文逗号隔开
    private String bgRoomTips;

    // 所属商家
    private String bgRoomMerchantId;

    // 房屋性质 来源数据字典
    private String bgRoomProperty;

    // 房屋类型 来源数据字典
    private String bgRoomType;

    // 房屋详情
    private String bgRoomDetail;

    // 是否删除 0-否 1-是
    private String isDelete;

    // 描述
    private String description;

    // 创建者
    private String createBy;

    // 创建时间
    private Date createTime;

    // 更新者
    private String updateBy;

    // 更新时间
    private Date updateTime;

    // 备注 预留字段
    private String remark;

    /**
     * 
     * @return bg_room_id 后台房源id,唯一标识
     */
    public String getBgRoomId() {
        return bgRoomId;
    }

    /**
     * 
     * @param bgRoomId 后台房源id,唯一标识
     */
    public void setBgRoomId(String bgRoomId) {
        this.bgRoomId = bgRoomId == null ? null : bgRoomId.trim();
    }

    /**
     * 
     * @return bg_room_number 房源编号
     */
    public String getBgRoomNumber() {
        return bgRoomNumber;
    }

    /**
     * 
     * @param bgRoomNumber 房源编号
     */
    public void setBgRoomNumber(String bgRoomNumber) {
        this.bgRoomNumber = bgRoomNumber == null ? null : bgRoomNumber.trim();
    }

    /**
     * 
     * @return bg_room_name 房源名称
     */
    public String getBgRoomName() {
        return bgRoomName;
    }

    /**
     * 
     * @param bgRoomName 房源名称
     */
    public void setBgRoomName(String bgRoomName) {
        this.bgRoomName = bgRoomName == null ? null : bgRoomName.trim();
    }

    /**
     * 
     * @return bg_room_address_country 所属国籍
     */
    public String getBgRoomAddressCountry() {
        return bgRoomAddressCountry;
    }

    /**
     * 
     * @param bgRoomAddressCountry 所属国籍
     */
    public void setBgRoomAddressCountry(String bgRoomAddressCountry) {
        this.bgRoomAddressCountry = bgRoomAddressCountry == null ? null : bgRoomAddressCountry.trim();
    }

    /**
     * 
     * @return bg_room_address_city 所属城市
     */
    public String getBgRoomAddressCity() {
        return bgRoomAddressCity;
    }

    /**
     * 
     * @param bgRoomAddressCity 所属城市
     */
    public void setBgRoomAddressCity(String bgRoomAddressCity) {
        this.bgRoomAddressCity = bgRoomAddressCity == null ? null : bgRoomAddressCity.trim();
    }

    /**
     * 
     * @return bg_room_address_detail 详细地址
     */
    public String getBgRoomAddressDetail() {
        return bgRoomAddressDetail;
    }

    /**
     * 
     * @param bgRoomAddressDetail 详细地址
     */
    public void setBgRoomAddressDetail(String bgRoomAddressDetail) {
        this.bgRoomAddressDetail = bgRoomAddressDetail == null ? null : bgRoomAddressDetail.trim();
    }

    /**
     * 
     * @return bg_room_address_x 经度
     */
    public Double getBgRoomAddressX() {
        return bgRoomAddressX;
    }

    /**
     * 
     * @param bgRoomAddressX 经度
     */
    public void setBgRoomAddressX(Double bgRoomAddressX) {
        this.bgRoomAddressX = bgRoomAddressX;
    }

    /**
     * 
     * @return bg_room_address_y 纬度
     */
    public Double getBgRoomAddressY() {
        return bgRoomAddressY;
    }

    /**
     * 
     * @param bgRoomAddressY 纬度
     */
    public void setBgRoomAddressY(Double bgRoomAddressY) {
        this.bgRoomAddressY = bgRoomAddressY;
    }

    /**
     * 
     * @return bg_room_price 价格
     */
    public Double getBgRoomPrice() {
        return bgRoomPrice;
    }

    /**
     * 
     * @param bgRoomPrice 价格
     */
    public void setBgRoomPrice(Double bgRoomPrice) {
        this.bgRoomPrice = bgRoomPrice;
    }

    /**
     * 
     * @return bg_room_price_unit 单位 0-周 1-月 2-年
     */
    public String getBgRoomPriceUnit() {
        return bgRoomPriceUnit;
    }

    /**
     * 
     * @param bgRoomPriceUnit 单位 0-周 1-月 2-年
     */
    public void setBgRoomPriceUnit(String bgRoomPriceUnit) {
        this.bgRoomPriceUnit = bgRoomPriceUnit == null ? null : bgRoomPriceUnit.trim();
    }

    /**
     * 
     * @return bg_room_status 房源状态 1-出租中 2-已被预订 3-房源锁定 4-待签租房合同 5-已出租 6-已下架
     */
    public String getBgRoomStatus() {
        return bgRoomStatus;
    }

    /**
     * 
     * @param bgRoomStatus 房源状态 1-出租中 2-已被预订 3-房源锁定 4-待签租房合同 5-已出租 6-已下架
     */
    public void setBgRoomStatus(String bgRoomStatus) {
        this.bgRoomStatus = bgRoomStatus == null ? null : bgRoomStatus.trim();
    }

    /**
     * 
     * @return bg_room_max_appoint_num 最大预约数
     */
    public Byte getBgRoomMaxAppointNum() {
        return bgRoomMaxAppointNum;
    }

    /**
     * 
     * @param bgRoomMaxAppointNum 最大预约数
     */
    public void setBgRoomMaxAppointNum(Byte bgRoomMaxAppointNum) {
        this.bgRoomMaxAppointNum = bgRoomMaxAppointNum;
    }

    /**
     * 
     * @return bg_room_recommend 是否推荐房源 0-否 1-是
     */
    public String getBgRoomRecommend() {
        return bgRoomRecommend;
    }

    /**
     * 
     * @param bgRoomRecommend 是否推荐房源 0-否 1-是
     */
    public void setBgRoomRecommend(String bgRoomRecommend) {
        this.bgRoomRecommend = bgRoomRecommend == null ? null : bgRoomRecommend.trim();
    }

    /**
     * 
     * @return bg_room_tips 房源标签 多个英文逗号隔开
     */
    public String getBgRoomTips() {
        return bgRoomTips;
    }

    /**
     * 
     * @param bgRoomTips 房源标签 多个英文逗号隔开
     */
    public void setBgRoomTips(String bgRoomTips) {
        this.bgRoomTips = bgRoomTips == null ? null : bgRoomTips.trim();
    }

    /**
     * 
     * @return bg_room_merchant_id 所属商家
     */
    public String getBgRoomMerchantId() {
        return bgRoomMerchantId;
    }

    /**
     * 
     * @param bgRoomMerchantId 所属商家
     */
    public void setBgRoomMerchantId(String bgRoomMerchantId) {
        this.bgRoomMerchantId = bgRoomMerchantId == null ? null : bgRoomMerchantId.trim();
    }

    /**
     * 
     * @return bg_room_property 房屋性质 来源数据字典
     */
    public String getBgRoomProperty() {
        return bgRoomProperty;
    }

    /**
     * 
     * @param bgRoomProperty 房屋性质 来源数据字典
     */
    public void setBgRoomProperty(String bgRoomProperty) {
        this.bgRoomProperty = bgRoomProperty == null ? null : bgRoomProperty.trim();
    }

    /**
     * 
     * @return bg_room_type 房屋类型 来源数据字典
     */
    public String getBgRoomType() {
        return bgRoomType;
    }

    /**
     * 
     * @param bgRoomType 房屋类型 来源数据字典
     */
    public void setBgRoomType(String bgRoomType) {
        this.bgRoomType = bgRoomType == null ? null : bgRoomType.trim();
    }

    /**
     * 
     * @return bg_room_detail 房屋详情
     */
    public String getBgRoomDetail() {
        return bgRoomDetail;
    }

    /**
     * 
     * @param bgRoomDetail 房屋详情
     */
    public void setBgRoomDetail(String bgRoomDetail) {
        this.bgRoomDetail = bgRoomDetail == null ? null : bgRoomDetail.trim();
    }

    /**
     * 
     * @return is_delete 是否删除 0-否 1-是
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 
     * @param isDelete 是否删除 0-否 1-是
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 
     * @return create_by 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_by 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * 
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return remark 备注 预留字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 备注 预留字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}