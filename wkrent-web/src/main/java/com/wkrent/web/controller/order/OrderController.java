package com.wkrent.web.controller.order;

import com.wkrent.business.bg.ordermanagement.service.OrderService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgOrderVO;
import com.wkrent.common.entity.vo.BgRoomVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * 订单管理
 */
@Api(value = "order", tags = "预约订单接口")
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "条件查询订单信息", notes = "条件查询订单信息", httpMethod = "POST", response = BgRoomVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgOrderVO> findByCondition(@RequestBody @ApiParam(name = "orderVO", value = "订单查询条件")
                                                             BgOrderVO orderVO){
        return orderService.findByCondition(orderVO);
    }

    @ApiOperation(value = "根据id查询订单信息", notes = "根据id查询订单信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findById(@RequestBody @ApiParam(name = "orderId", value = "订单id(仅传入bgOrderId即可)")
                                       BgOrderVO orderVO){
        return orderService.findByOrderId(orderVO.getBgOrderId());
    }

    @ApiOperation(value = "初始化运营处理", notes = "初始化运营处理", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/initManage", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO initManage(@RequestBody @ApiParam(name = "orderId", value = "订单id(仅传入bgOrderId即可)")
                                         BgOrderVO orderVO){
        return orderService.findByOrderId(orderVO.getBgOrderId());
    }

    /**
     * 运营管理
     */
    @ApiOperation(value = "锁定房源", notes = "锁定房源", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/lockRoom", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO lockRoom(@RequestBody @ApiParam(name = "orderVO", value = "订单信息")
                                     BgOrderVO orderVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            orderService.lockRoom(orderVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("订单锁定房源失败", e, orderVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("订单锁定房源失败，系统异常", e, orderVO);
        }
        return baseAjaxVO;
    }

    /**
     * 运营管理
     */
    @ApiOperation(value = "缴纳房租", notes = "缴纳房租", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/payRent", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO payRent(@RequestBody @ApiParam(name = "orderVO", value = "订单信息")
                                       BgOrderVO orderVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            orderService.payRent(orderVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("订单缴纳房租失败", e, orderVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("订单缴纳房租失败，系统异常", e, orderVO);
        }
        return baseAjaxVO;
    }

    /**
     * 运营管理
     */
    @ApiOperation(value = "签订合同", notes = "签订合同", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/signContract", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO signContract(@RequestBody @ApiParam(name = "orderVO", value = "订单信息")
                                      BgOrderVO orderVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            orderService.signContract(orderVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("订单签订合同失败", e, orderVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("订单签订合同失败，系统异常", e, orderVO);
        }
        return baseAjaxVO;
    }


    @ApiOperation(value = "删除订单信息", notes = "删除订单信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "bgOrderId", value = "订单id(传入bgOrderId)")
                                     BgOrderVO orderVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            orderService.delete(orderVO.getBgOrderId(), getLoginAccount(session), baseAjaxVO);
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除订单信息失败", e, orderVO.getBgOrderId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除订单信息失败，系统异常", e, orderVO.getBgOrderId());
        }
        return baseAjaxVO;
    }
}
