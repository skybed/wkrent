/*
*
* BgOrder.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 * @author skybed
 *
 */
public class BgOrderVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8338643554945370059L;

    /**
     * 订单id,唯一标识
     */
    private String bgOrderId;

    /**
     * 订单编号
     */
    private String bgOrderNumber;

    /**
     * 订单创建时间
     */
    private Date bgOrderCreateTime;

    /**
     * 预订人id
     */
    private String bgOrderUserId;

    /**
     * 预定房源id
     */
    private String bgOrderRoomId;

    /**
     * 订单状态 0-预约中 1-已取消预约 2-房源锁定 3-房屋已出租 4-待签合同 5-已完成
     */
    private String bgOrderStatus;

    /**
     * 入住时间
     */
    private Date bgOrderCheckinDate;

    /**
     * 租期
     */
    private String bgOrderRentTenancy;

    /**
     * 是否缴纳押金 0-否 1-是
     */
    private String bgOrderRentDepositFlag;

    /**
     * 押金
     */
    private Double bgOrderRentDepositMoney;

    /**
     * 押金单位 0-英镑 1-人民币
     */
    private String bgOrderRentDepositUnit;

    /**
     * 押金流水号
     */
    private String bgOrderRentDepositSerialnum;

    /**
     * 是否缴纳服务费 0-否 1-是
     */
    private String bgOrderRentServiceFlag;

    /**
     * 服务费
     */
    private Double bgOrderRentServiceMoney;

    /**
     * 服务费单位 0-英镑 1-人民币
     */
    private String bgOrderRentServiceUnit;

    /**
     *  服务费流水号
     */
    private String bgOrderRentServiceSeralnum;

    /**
     * 是否缴纳租金 0-否 1-是
     */
    private String bgOrderRentFlag;

    /**
     * 租金
     */
    private Double bgOrderRentMoney;

    /**
     * 租金单位 0-英镑 1-人民币
     */
    private String bgOrderRentUnit;

    /**
     * 租金流水号
     */
    private String bgOrderRentSerialnum;

    /**
     * 是否签订合同 0-否 1-是
     */
    private String bgOrderRentContractFlag;

    /**
     * 合同号
     */
    private String bgOrderRentContractSerialnum;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     *  更新时间
     */
    private Date updateTime;

    /**
     * 分页信息
     */
    private Page page;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 订单idList
     */
    private List<String> orderIdList;

    /**
     * 下单人姓名
     */
    private String orderUserName;

    /**
     * 下单人电话
     */
    private String orderUserPhone;

    /**
     * 下单人性别
     */
    private String orderUserSex;

    /**
     * 房源编号
     */
    private String bgRoomNumber;



    /**
     * 
     * @return bg_order_id 订单id,唯一标识
     */
    public String getBgOrderId() {
        return bgOrderId;
    }

    /**
     * 
     * @param bgOrderId 订单id,唯一标识
     */
    public void setBgOrderId(String bgOrderId) {
        this.bgOrderId = bgOrderId == null ? null : bgOrderId.trim();
    }

    /**
     * 
     * @return bg_order_number 订单编号
     */
    public String getBgOrderNumber() {
        return bgOrderNumber;
    }

    /**
     * 
     * @param bgOrderNumber 订单编号
     */
    public void setBgOrderNumber(String bgOrderNumber) {
        this.bgOrderNumber = bgOrderNumber == null ? null : bgOrderNumber.trim();
    }

    /**
     * 
     * @return bg_order_create_time 订单创建时间
     */
    public Date getBgOrderCreateTime() {
        return bgOrderCreateTime;
    }

    /**
     * 
     * @param bgOrderCreateTime 订单创建时间
     */
    public void setBgOrderCreateTime(Date bgOrderCreateTime) {
        this.bgOrderCreateTime = bgOrderCreateTime;
    }

    /**
     * 
     * @return bg_order_user_id 预订人id
     */
    public String getBgOrderUserId() {
        return bgOrderUserId;
    }

    /**
     * 
     * @param bgOrderUserId 预订人id
     */
    public void setBgOrderUserId(String bgOrderUserId) {
        this.bgOrderUserId = bgOrderUserId == null ? null : bgOrderUserId.trim();
    }

    /**
     * 
     * @return bg_order_room_id 预定房源id
     */
    public String getBgOrderRoomId() {
        return bgOrderRoomId;
    }

    /**
     * 
     * @param bgOrderRoomId 预定房源id
     */
    public void setBgOrderRoomId(String bgOrderRoomId) {
        this.bgOrderRoomId = bgOrderRoomId == null ? null : bgOrderRoomId.trim();
    }

    /**
     * 
     * @return bg_order_status 订单状态 0-预约中 1-已取消预约 2-房源锁定 3-房屋已出租 4-待签合同 5-已完成 6-房源已下架
     */
    public String getBgOrderStatus() {
        return bgOrderStatus;
    }

    /**
     * 
     * @param bgOrderStatus 订单状态 0-预约中 1-已取消预约 2-房源锁定 3-房屋已出租 4-待签合同 5-已完成 6-房源已下架
     */
    public void setBgOrderStatus(String bgOrderStatus) {
        this.bgOrderStatus = bgOrderStatus == null ? null : bgOrderStatus.trim();
    }

    /**
     * 
     * @return bg_order_checkIn_date 入住时间
     */
    public Date getBgOrderCheckinDate() {
        return bgOrderCheckinDate;
    }

    /**
     * 
     * @param bgOrderCheckinDate 入住时间
     */
    public void setBgOrderCheckinDate(Date bgOrderCheckinDate) {
        this.bgOrderCheckinDate = bgOrderCheckinDate;
    }

    /**
     * 
     * @return bg_order_rent_tenancy 租期
     */
    public String getBgOrderRentTenancy() {
        return bgOrderRentTenancy;
    }

    /**
     * 
     * @param bgOrderRentTenancy 租期
     */
    public void setBgOrderRentTenancy(String bgOrderRentTenancy) {
        this.bgOrderRentTenancy = bgOrderRentTenancy == null ? null : bgOrderRentTenancy.trim();
    }

    /**
     * 
     * @return bg_order_rent_deposit_flag 是否缴纳押金 0-否 1-是
     */
    public String getBgOrderRentDepositFlag() {
        return bgOrderRentDepositFlag;
    }

    /**
     * 
     * @param bgOrderRentDepositFlag 是否缴纳押金 0-否 1-是
     */
    public void setBgOrderRentDepositFlag(String bgOrderRentDepositFlag) {
        this.bgOrderRentDepositFlag = bgOrderRentDepositFlag == null ? null : bgOrderRentDepositFlag.trim();
    }

    /**
     * 
     * @return bg_order_rent_deposit_money 押金
     */
    public Double getBgOrderRentDepositMoney() {
        return bgOrderRentDepositMoney;
    }

    /**
     * 
     * @param bgOrderRentDepositMoney 押金
     */
    public void setBgOrderRentDepositMoney(Double bgOrderRentDepositMoney) {
        this.bgOrderRentDepositMoney = bgOrderRentDepositMoney;
    }

    /**
     * 
     * @return bg_order_rent_deposit_unit 押金单位 0-英镑 1-人民币
     */
    public String getBgOrderRentDepositUnit() {
        return bgOrderRentDepositUnit;
    }

    /**
     * 
     * @param bgOrderRentDepositUnit 押金单位 0-英镑 1-人民币
     */
    public void setBgOrderRentDepositUnit(String bgOrderRentDepositUnit) {
        this.bgOrderRentDepositUnit = bgOrderRentDepositUnit == null ? null : bgOrderRentDepositUnit.trim();
    }

    /**
     * 
     * @return bg_order_rent_deposit_serialNum 押金流水号
     */
    public String getBgOrderRentDepositSerialnum() {
        return bgOrderRentDepositSerialnum;
    }

    /**
     * 
     * @param bgOrderRentDepositSerialnum 押金流水号
     */
    public void setBgOrderRentDepositSerialnum(String bgOrderRentDepositSerialnum) {
        this.bgOrderRentDepositSerialnum = bgOrderRentDepositSerialnum == null ? null : bgOrderRentDepositSerialnum.trim();
    }

    /**
     * 
     * @return bg_order_rent_service_flag 是否缴纳服务费 0-否 1-是
     */
    public String getBgOrderRentServiceFlag() {
        return bgOrderRentServiceFlag;
    }

    /**
     * 
     * @param bgOrderRentServiceFlag 是否缴纳服务费 0-否 1-是
     */
    public void setBgOrderRentServiceFlag(String bgOrderRentServiceFlag) {
        this.bgOrderRentServiceFlag = bgOrderRentServiceFlag == null ? null : bgOrderRentServiceFlag.trim();
    }

    /**
     * 
     * @return bg_order_rent_service_money 服务费
     */
    public Double getBgOrderRentServiceMoney() {
        return bgOrderRentServiceMoney;
    }

    /**
     * 
     * @param bgOrderRentServiceMoney 服务费
     */
    public void setBgOrderRentServiceMoney(Double bgOrderRentServiceMoney) {
        this.bgOrderRentServiceMoney = bgOrderRentServiceMoney;
    }

    /**
     * 
     * @return bg_order_rent_service_unit 服务费单位 0-英镑 1-人民币
     */
    public String getBgOrderRentServiceUnit() {
        return bgOrderRentServiceUnit;
    }

    /**
     * 
     * @param bgOrderRentServiceUnit 服务费单位 0-英镑 1-人民币
     */
    public void setBgOrderRentServiceUnit(String bgOrderRentServiceUnit) {
        this.bgOrderRentServiceUnit = bgOrderRentServiceUnit == null ? null : bgOrderRentServiceUnit.trim();
    }

    /**
     * 
     * @return bg_order_rent_service_seralNum 服务费流水号
     */
    public String getBgOrderRentServiceSeralnum() {
        return bgOrderRentServiceSeralnum;
    }

    /**
     * 
     * @param bgOrderRentServiceSeralnum 服务费流水号
     */
    public void setBgOrderRentServiceSeralnum(String bgOrderRentServiceSeralnum) {
        this.bgOrderRentServiceSeralnum = bgOrderRentServiceSeralnum == null ? null : bgOrderRentServiceSeralnum.trim();
    }

    /**
     * 
     * @return bg_order_rent_flag 是否缴纳租金 0-否 1-是
     */
    public String getBgOrderRentFlag() {
        return bgOrderRentFlag;
    }

    /**
     * 
     * @param bgOrderRentFlag 是否缴纳租金 0-否 1-是
     */
    public void setBgOrderRentFlag(String bgOrderRentFlag) {
        this.bgOrderRentFlag = bgOrderRentFlag == null ? null : bgOrderRentFlag.trim();
    }

    /**
     * 
     * @return bg_order_rent_money 租金
     */
    public Double getBgOrderRentMoney() {
        return bgOrderRentMoney;
    }

    /**
     * 
     * @param bgOrderRentMoney 租金
     */
    public void setBgOrderRentMoney(Double bgOrderRentMoney) {
        this.bgOrderRentMoney = bgOrderRentMoney;
    }

    /**
     * 
     * @return bg_order_rent_unit 租金单位 0-英镑 1-人民币
     */
    public String getBgOrderRentUnit() {
        return bgOrderRentUnit;
    }

    /**
     * 
     * @param bgOrderRentUnit 租金单位 0-英镑 1-人民币
     */
    public void setBgOrderRentUnit(String bgOrderRentUnit) {
        this.bgOrderRentUnit = bgOrderRentUnit == null ? null : bgOrderRentUnit.trim();
    }

    /**
     * 
     * @return bg_order_rent_serialNum 租金流水号
     */
    public String getBgOrderRentSerialnum() {
        return bgOrderRentSerialnum;
    }

    /**
     * 
     * @param bgOrderRentSerialnum 租金流水号
     */
    public void setBgOrderRentSerialnum(String bgOrderRentSerialnum) {
        this.bgOrderRentSerialnum = bgOrderRentSerialnum == null ? null : bgOrderRentSerialnum.trim();
    }

    /**
     * 
     * @return bg_order_rent_contract_flag 是否签订合同 0-否 1-是
     */
    public String getBgOrderRentContractFlag() {
        return bgOrderRentContractFlag;
    }

    /**
     * 
     * @param bgOrderRentContractFlag 是否签订合同 0-否 1-是
     */
    public void setBgOrderRentContractFlag(String bgOrderRentContractFlag) {
        this.bgOrderRentContractFlag = bgOrderRentContractFlag == null ? null : bgOrderRentContractFlag.trim();
    }

    /**
     * 
     * @return bg_order_rent_contract_serialNum 合同号
     */
    public String getBgOrderRentContractSerialnum() {
        return bgOrderRentContractSerialnum;
    }

    /**
     * 
     * @param bgOrderRentContractSerialnum 合同号
     */
    public void setBgOrderRentContractSerialnum(String bgOrderRentContractSerialnum) {
        this.bgOrderRentContractSerialnum = bgOrderRentContractSerialnum == null ? null : bgOrderRentContractSerialnum.trim();
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderUserPhone() {
        return orderUserPhone;
    }

    public void setOrderUserPhone(String orderUserPhone) {
        this.orderUserPhone = orderUserPhone;
    }

    public String getOrderUserSex() {
        return orderUserSex;
    }

    public void setOrderUserSex(String orderUserSex) {
        this.orderUserSex = orderUserSex;
    }

    public String getBgRoomNumber() {
        return bgRoomNumber;
    }

    public void setBgRoomNumber(String bgRoomNumber) {
        this.bgRoomNumber = bgRoomNumber;
    }
}