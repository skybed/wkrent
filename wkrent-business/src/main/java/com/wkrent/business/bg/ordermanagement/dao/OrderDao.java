package com.wkrent.business.bg.ordermanagement.dao;

import com.wkrent.common.entity.po.BgOrder;
import com.wkrent.common.entity.vo.BgOrderVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface OrderDao {

    /**
     * 条件查询订单信息
     * @param orderVO 查询条件
     * @return 符合条件结果
     */
    List<BgOrderVO> findByCondition(BgOrderVO orderVO);

    /**
     * 根据条件查询订单总条数
     * @param orderVO 查询条件
     * @return 总条数
     */
    int countByCondition(BgOrderVO orderVO);

    /**
     * 更新订单状态
     * @param order 更新订单
     * @return 更新条数
     */
    int updateStatus(BgOrder order);

    /**
     * 锁定订单
     * @param order 更新订单
     * @return 更新条数
     */
    int lockOrder(BgOrder order);

    /**
     * 缴纳房租
     * @param order 更新订单
     * @return 更新条数
     */
    int orderPayRent(BgOrder order);

    /**
     * 签订合同
     * @param order 更新订单
     * @return 更新条数
     */
    int signContract(BgOrder order);

    /**
     * 根据房源id更新其他订单状态为已出租
     * @param order 更新订单
     * @return 更新条数
     */
    int updateOtherOrderStatus(BgOrder order);

    /**
     * 根据房源id更新订单状态
     * @param order 更新订单
     * @return 更新条数
     */
    int updateStatusByRoomId(BgOrder order);

    /**
     * 根据id查询订单
     * @param orderId 订单id
     * @return 符合条件未被删除订单信息
     */
    BgOrder findById(String orderId);

    /**
     * 根据id查询订单
     * @param orderIdList 订单id
     * @return 符合条件未被删除订单信息
     */
    List<BgOrder> findByIdList(List<String> orderIdList);

    /**
     * 删除订单
     * @param order 订单信息
     * @return 成功条数
     */
    int delete(BgOrderVO order);
}