package com.wkrent.business.bg.ordermanagement.service.impl;

import com.wkrent.business.bg.ordermanagement.dao.OrderDao;
import com.wkrent.business.bg.ordermanagement.service.OrderService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.AppFeedBackStatusEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.AppFeedback;
import com.wkrent.common.entity.po.BgOrder;
import com.wkrent.common.entity.vo.BgOrderVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 分页查询用户信息
     *
     * @param orderVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgOrderVO> findByCondition(BgOrderVO orderVO) {
        PageResult<BgOrderVO> pageResult = new PageResult<>();

        int total = orderDao.countByCondition(orderVO);
        if(total > 0){
            List<BgOrderVO> feeBackList = orderDao.findByCondition(orderVO);
            pageResult.setRows(feeBackList);
            pageResult.setTotal(total);
        }
        return pageResult;
    }


    /**
     * 根据id删除用户
     *
     * @param orderIdList 订单idList
     */
    @Override
    public void delete(List<String> orderIdList, String loginAccount) {
        if(CollectionUtils.isEmpty(orderIdList)){
            throw new WkRentException("删除用户失败，请传入用户id");
        }
        //TODO 删除前校验
        BgOrderVO orderVO = new BgOrderVO();
        orderVO.setOrderIdList(orderIdList);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, orderVO, loginAccount);
        int result = orderDao.delete(orderVO);
        if(result != 1){
            throw new WkRentException("删除订单失败，订单信息已被删除！");
        }
    }

    /**
     * 根据orderId查询前台订单信息
     *
     * @param orderId 前台订单id
     * @return 未被删除user
     */
    @Override
    public BaseAjaxVO findByOrderId(String orderId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(orderId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("订单Id不能为空");
            return baseAjaxVO;
        }
        BgOrder order = orderDao.findById(orderId);
        if(order == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("订单不存在或已被删除！");
            return baseAjaxVO;
        }
        baseAjaxVO.setResult(BeanUtil.copyBean(order, BgOrderVO.class));
        return baseAjaxVO;
    }

    @Override
    public void manage(BgOrderVO orderVO, String loginAccount) {
//        if(StringUtils.isBlank(orderVO.getBgOrderId())){
//            throw new WkRentException("订单Id不能为空");
//        }
//        if(.getByCode(orderVO.getAppFeedbackStatus()) == null){
//            throw new WkRentException("订单状态有误！");
//        }
//        AppFeedback appFeedback = new AppFeedback();
//        appFeedback.setApporderId(orderVO.getApporderId());
//        appFeedback.setAppFeedbackStatus(orderVO.getAppFeedbackStatus());
//        appFeedback.setDescription(orderVO.getDescription());
//        orderDao.updateStatus(appFeedback);
    }

}
