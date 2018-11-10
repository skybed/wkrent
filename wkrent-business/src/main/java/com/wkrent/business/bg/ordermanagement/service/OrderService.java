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
     */
    void delete(List<String> orderIdList, String loginAccount);

    /**
     * 根据orderId查询前台订单信息
     * @param orderId 前台订单id
     * @return 未被删除user
     */
    BaseAjaxVO findByOrderId(String orderId);

    /**
     * 运营管理
     * @param orderVO 订单
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    void manage(BgOrderVO orderVO, String loginAccount);
}
