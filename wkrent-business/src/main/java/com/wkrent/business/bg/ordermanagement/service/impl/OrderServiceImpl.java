package com.wkrent.business.bg.ordermanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.ordermanagement.dao.OrderDao;
import com.wkrent.business.bg.ordermanagement.service.OrderService;
import com.wkrent.business.bg.roommanagement.service.BgRoomService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.AppFeedBackStatusEnum;
import com.wkrent.common.entity.enums.CurrencyEnum;
import com.wkrent.common.entity.enums.OrderStatusEnum;
import com.wkrent.common.entity.enums.RoomStatusEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.AppFeedback;
import com.wkrent.common.entity.po.BgOrder;
import com.wkrent.common.entity.vo.BgOrderVO;
import com.wkrent.common.entity.vo.BgRoomVO;
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

    @Autowired
    private BgRoomService bgRoomService;

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
    public void delete(List<String> orderIdList, String loginAccount, BaseAjaxVO result) {
        //删除前校验，只有已完成 or 房源已出租 or 已取消预订可删除
        List<String> deleteIdList = checkOrderStatus(orderIdList, result);
        if(CollectionUtils.isEmpty(deleteIdList)){
            throw new WkRentException("删除订单失败，当前订单均不可删除");
        }
        BgOrderVO orderVO = new BgOrderVO();
        orderVO.setOrderIdList(deleteIdList);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, orderVO, loginAccount);
        orderDao.delete(orderVO);
    }

    private List<String> checkOrderStatus(List<String> orderIdList, BaseAjaxVO result){
        List<String> deleteIdList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(orderIdList)){
            return deleteIdList;
        }
        List<BgOrder> orderList = orderDao.findByIdList(orderIdList);
        StringBuilder builder = new StringBuilder();
        for(BgOrder order : orderList){
            //删除前校验，只有已完成 or 房源已出租 or 已取消预订可删除
            if(OrderStatusEnum.ROOM_RENTED.getCode().equals(order.getBgOrderStatus()) ||
                    OrderStatusEnum.FINISH.getCode().equals(order.getBgOrderStatus()) ||
                    OrderStatusEnum.ORDER_CANCEL.getCode().equals(order.getBgOrderStatus())){
                deleteIdList.add(order.getBgOrderId());
            }else {
                builder.append(order.getBgOrderNumber()).append("、");
            }
        }
        if(StringUtils.isNotBlank(builder.toString())){
            builder.insert(0, "订单编号：");
            builder.append("删除失败，订单不允许删除");
        }
        result.setText(builder.toString());
        return deleteIdList;
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

    /**
     * 根据房源id更新订单状态
     *
     * @param roomId       房源id
     * @param orderStatus 订单状态
     * @param loginAccount 登录人账号
     */
    @Override
    public void updateStatusByRoomId(String roomId, String orderStatus, String loginAccount) {
        if(StringUtils.isBlank(roomId) || StringUtils.isBlank(orderStatus)
                || StringUtils.isBlank(loginAccount)){
            return;
        }
        BgOrder order = new BgOrder();
        order.setBgOrderRoomId(roomId);
        order.setBgOrderStatus(orderStatus);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, order, loginAccount);
        orderDao.updateStatusByRoomId(order);
    }

    @Override
    public void lockRoom(BgOrderVO orderVO, String loginAccount) {
        if(StringUtils.isBlank(orderVO.getBgOrderId())){
            throw new WkRentException("订单Id不能为空");
        }
        if(StringUtils.isBlank(orderVO.getBgOrderRentDepositFlag())
                || StringUtils.isBlank(orderVO.getBgOrderRentServiceFlag())){
            throw new WkRentException("押金和平台服务费必须缴纳才能锁定房源");
        }
        if(CurrencyEnum.getByCode(orderVO.getBgOrderRentDepositUnit()) == null){
            throw new WkRentException("押金币种错误");
        }
        if(CurrencyEnum.getByCode(orderVO.getBgOrderRentServiceUnit()) == null){
            throw new WkRentException("平台服务费币种错误");
        }
        if(orderVO.getBgOrderRentDepositMoney() == null
                || orderVO.getBgOrderRentDepositMoney().compareTo(0d) <= 0){
            throw new WkRentException("押金不能为空或者小于等于0");
        }
        if(orderVO.getBgOrderRentServiceMoney() == null
                || orderVO.getBgOrderRentServiceMoney().compareTo(0d) <= 0){
            throw new WkRentException("平台服务费不能为空或者小于等于0");
        }
        if(StringUtils.isBlank(orderVO.getBgOrderRentDepositSerialnum())){
            throw new WkRentException("押金流水号不能为空");
        }
        if(StringUtils.isBlank(orderVO.getBgOrderRentServiceSeralnum())){
            throw new WkRentException("平台服务费流水号不能为空");
        }
        BgOrder bgOrder = orderDao.findById(orderVO.getBgOrderId());
        if(bgOrder == null){
            throw new WkRentException("订单锁定房源失败，订单不存在或已被删除");
        }
        //订单不为预定中，不允许锁定房源
        if(!OrderStatusEnum.ORDERING.getCode().equals(bgOrder.getBgOrderStatus())){
            throw new WkRentException("订单锁定房源失败，订单状态不为预约中！");
        }
        BgOrder order = BeanUtil.copyBean(orderVO, BgOrder.class);
        order.setBgOrderStatus(OrderStatusEnum.LOCKED.getCode());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, order, loginAccount);

        orderDao.lockOrder(order);

        //更新房源状态为锁定房源
        BgRoomVO bgRoomVO = new BgRoomVO();
        bgRoomVO.setBgRoomStatus(RoomStatusEnum.LOCKED.getCode());
        bgRoomVO.setBgRoomId(bgOrder.getBgOrderRoomId());
        bgRoomService.updateRoomStatusById(bgRoomVO, loginAccount);

        //更新其他订单房源状态为已出租
        BgOrder updateStatus = new BgOrder();
        updateStatus.setBgOrderId(order.getBgOrderId());
        updateStatus.setBgOrderRoomId(bgOrder.getBgOrderRoomId());
        updateStatus.setBgOrderStatus(OrderStatusEnum.ROOM_RENTED.getCode());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateStatus, loginAccount);
        orderDao.updateOtherOrderStatus(updateStatus);
    }

    /**
     * 缴纳房租
     *
     * @param orderVO      订单
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    @Override
    public void payRent(BgOrderVO orderVO, String loginAccount) {

        if(Constants.STR_TRUE.equals(orderVO.getBgOrderRentFlag())){
            throw new WkRentException("是否缴纳房租必须勾选");
        }
        if(CurrencyEnum.getByCode(orderVO.getBgOrderRentUnit()) == null){
            throw new WkRentException("房租币种错误");
        }
        if(orderVO.getBgOrderRentMoney() == null
                || orderVO.getBgOrderRentMoney().compareTo(0d) <= 0){
            throw new WkRentException("租金不能为空或者小于等于0");
        }
        if(StringUtils.isBlank(orderVO.getBgOrderRentSerialnum())){
            throw new WkRentException("租金流水号不能为空");
        }
        BgOrder bgOrder = orderDao.findById(orderVO.getBgOrderId());
        if(bgOrder == null){
            throw new WkRentException("订单缴纳租金失败，订单不存在或已被删除");
        }
        //订单不为 锁定，不允许缴纳租金
        if(!OrderStatusEnum.LOCKED.getCode().equals(bgOrder.getBgOrderStatus())){
            throw new WkRentException("订单缴纳租金失败，订单状态不为房源锁定！");
        }

        BgOrder order = BeanUtil.copyBean(orderVO, BgOrder.class);
        order.setBgOrderStatus(OrderStatusEnum.SIGN_CONTRACT.getCode());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, order, loginAccount);

        orderDao.orderPayRent(order);
        //更新房源状态为待签订租房合同
        BgRoomVO bgRoomVO = new BgRoomVO();
        bgRoomVO.setBgRoomStatus(RoomStatusEnum.SIGN_CONTRACT.getCode());
        bgRoomVO.setBgRoomId(bgOrder.getBgOrderRoomId());
        bgRoomService.updateRoomStatusById(bgRoomVO, loginAccount);
    }

    /**
     * 签订合同
     *
     * @param orderVO      订单
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    @Override
    public void signContract(BgOrderVO orderVO, String loginAccount) {
        if(Constants.STR_TRUE.equals(orderVO.getBgOrderRentContractFlag())){
            throw new WkRentException("是否签订合同必须勾选");
        }
        if(StringUtils.isBlank(orderVO.getBgOrderRentContractSerialnum())){
            throw new WkRentException("合同编号不能为空");
        }
        BgOrder bgOrder = orderDao.findById(orderVO.getBgOrderId());
        if(bgOrder == null){
            throw new WkRentException("订单签订合同失败，订单不存在或已被删除");
        }
        //订单不为 待签订合同，不允许签订合同
        if(!OrderStatusEnum.SIGN_CONTRACT.getCode().equals(bgOrder.getBgOrderStatus())){
            throw new WkRentException("订单签订合同失败，订单状态不为待签订合同！");
        }

        BgOrder order = BeanUtil.copyBean(orderVO, BgOrder.class);
        order.setBgOrderStatus(OrderStatusEnum.FINISH.getCode());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, order, loginAccount);
        orderDao.signContract(order);

        //更新房源状态为已出租
        BgRoomVO bgRoomVO = new BgRoomVO();
        bgRoomVO.setBgRoomStatus(RoomStatusEnum.RENTED.getCode());
        bgRoomVO.setBgRoomId(bgOrder.getBgOrderRoomId());
        bgRoomService.updateRoomStatusById(bgRoomVO, loginAccount);
    }

}
