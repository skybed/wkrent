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
import java.util.List;

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
    public BaseAjaxVO findById(@RequestBody @ApiParam(name = "orderId", value = "订单id")
                                       String orderId){
        return orderService.findByOrderId(orderId);
    }

    @ApiOperation(value = "初始化运营处理", notes = "初始化运营处理", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/initManage", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO initManage(@RequestBody @ApiParam(name = "orderId", value = "订单id")
                                         String orderId){
        //TODO 待处理
        return orderService.findByOrderId(orderId);
    }

    /**
     * 运营管理
     */
    @ApiOperation(value = "订单运营处理", notes = "订单运营处理", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO manage(@RequestBody @ApiParam(name = "orderVO", value = "订单信息")
                                     BgOrderVO orderVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //TODO 逻辑待处理
            orderService.manage(orderVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("订单运营处理失败", e, orderVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("订单运营处理失败，系统异常", e, orderVO);
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "删除订单信息", notes = "删除订单信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "orderIdList", value = "待修改订单信息")
                                     List<String> orderIdList, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            orderService.delete(orderIdList, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除订单信息失败", e, orderIdList);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除订单信息失败，系统异常", e, orderIdList);
        }
        return baseAjaxVO;
    }
}
