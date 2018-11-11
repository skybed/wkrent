package com.wkrent.business.bg.ordermanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgOrderVO;

import java.util.List;

public interface OrderService {

    /**
     * 分页查询订单信息
     * @param orderVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgOrderVO> findByCondition(BgOrderVO orderVO);

    /**
     * 根据id删除订单
     * @param orderIdList 订单id
     * @param loginAccount 当前登录账号
     * @param result 返回提示
     */
    void delete(List<String> orderIdList, String loginAccount, BaseAjaxVO result);

    /**
     * 根据orderId查询前台订单信息
     * @param orderId 前台订单id
     * @return 未被删除user
     */
    BaseAjaxVO findByOrderId(String orderId);


    /**
     * 根据房源id更新订单状态
     * @param roomId 房源id
     * @param orderStatus 房源状态
     * @param loginAccount 登录账号
     */
    void updateStatusByRoomId(String roomId, String orderStatus, String loginAccount);

    /**
     * 锁定房源
     * @param orderVO 订单
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    void lockRoom(BgOrderVO orderVO, String loginAccount);

    /**
     * 缴纳房租
     * @param orderVO 订单
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    void payRent(BgOrderVO orderVO, String loginAccount);

    /**
     * 签订合同
     * @param orderVO 订单
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    void signContract(BgOrderVO orderVO, String loginAccount);
}
