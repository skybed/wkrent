package com.wkrent.app.order.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.app.base.obj.DataDict;
import com.wkrent.business.app.base.service.AppDataDictValueService;
import com.wkrent.business.app.order.obj.OrderDetailInfo;
import com.wkrent.business.app.order.obj.RentOrder;
import com.wkrent.business.app.order.service.AppOrderService;
import com.wkrent.business.app.picture.service.AppImageService;
import com.wkrent.business.app.rent.service.RentRoomInfoService;
import com.wkrent.business.app.rent.service.RentRoomService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.BgOrder;
import com.wkrent.common.entity.BgPicAttach;
import com.wkrent.common.entity.BgRoom;
import com.wkrent.common.entity.po.BgRoomInfo;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "order", tags = "订单信息")
@Controller
@RequestMapping("/app/api")
public class OrderController {
	
	@Autowired
	private AppOrderService appOrderService;
	
	@Autowired
	private RentRoomService rentRoomService;
	
	@Autowired
	private RentRoomInfoService rentRoomInfoService;
	
	@Autowired
	private AppImageService appImageService;
	
//	@Autowired
//	private AppUserService appUserService;
	
	@Autowired
	private AppDataDictValueService appDataDictValueService;

	/**
	  * 查看订单分页列表
	 * @param request
	 * @param userId
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "查看订单分页列表", notes = "查看订单分页列表", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getOrderListByPager.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getOrderListByPager(HttpServletRequest request, Integer currentIndex, Integer pageSize) {
		
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//获取登陆用户信息
		String userId = request.getSession().getAttribute("current_user_id").toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(userId)) {
			Integer count = appOrderService.countRentOrder(userId);
			List<RentOrder> rentOrders = appOrderService.getRentOrderByPager(userId, currentIndex, pageSize);
			
			map.put("count", count);
			map.put("list", rentOrders);
			map.put("flag", true);
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	  * 删除订单
	 * @param request
	 * @param orderId
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "删除订单", notes = "删除订单", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/deleteOrder.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteOrder(HttpServletRequest request, String orderId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//获取登陆用户信息
		String userId = request.getSession().getAttribute("current_user_id").toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(orderId)) {
			//判断订单是否可以删除
			BgOrder order = appOrderService.selectByPrimaryKey(orderId);
			if(order != null) {
				if("1".equals(order.getBgOrderStatus()) || "5".equals(order.getBgOrderStatus())) {
					appOrderService.deleteRentOrder(orderId, userId);
					map.put("flag", true);
				} else {
					resultData.setCode(Constant.RESULT_ORDER_CANNOT_DELETE_CODE);
					resultData.setMsg(Constant.RESULT_ORDER_CANNOT_DELETE_MSG);
				}
			} else {
				resultData.setCode(Constant.RESULT_ORDER_NOT_EXISTS_CODE);
				resultData.setMsg(Constant.RESULT_ORDER_NOT_EXISTS_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	  * 取消预约
	 * @param request
	 * @param orderId
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "取消预约", notes = "取消预约", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/cancelApponitOrder.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String cancelApponitOrder(HttpServletRequest request, String orderId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//获取登陆用户信息
		String userId = request.getSession().getAttribute("current_user_id").toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(orderId)) {
			//判断订单是否可以取消预约
			BgOrder order = appOrderService.selectByPrimaryKey(orderId);
			if(order != null) {
				if("0".equals(order.getBgOrderStatus())) {
					//取消订单预约
					appOrderService.cancelOrderAppoint(orderId, userId);
					
					//恢复房源预约数
					rentRoomInfoService.cancelAppoint(order.getBgOrderRoomId(), userId);
					
					map.put("flag", true);
				} else {
					resultData.setCode(Constant.RESULT_ORDER_CANNOT_CANCEL_CODE);
					resultData.setMsg(Constant.RESULT_ORDER_CANNOT_CANCEL_MSG);
				}
			} else {
				resultData.setCode(Constant.RESULT_ORDER_NOT_EXISTS_CODE);
				resultData.setMsg(Constant.RESULT_ORDER_NOT_EXISTS_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	  * 获取订单详情
	 * @param request
	 * @param orderId
	 * @return
	 */
	@ApiOperation(value = "获取订单详情", notes = "获取订单详情", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getOrderDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getOrderDetail(HttpServletRequest request, String orderId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		if(StringUtils.isNotEmpty(orderId)) {
			//判断订单是否可以删除
			BgOrder order = appOrderService.selectByPrimaryKey(orderId);
			if(order != null) {
				OrderDetailInfo orderInfo = new OrderDetailInfo();
				orderInfo.setOrderId(order.getBgOrderId());
				orderInfo.setRoomId(order.getBgOrderRoomId());
				
				//获取房源附件
				List<BgPicAttach> attachs = appImageService.selectByOwnerId(order.getBgOrderRoomId());
				if(CollectionUtils.isNotEmpty(attachs)) {
					orderInfo.setRoomPic(attachs.get(0).getPicAttachId());
				}
				
				//获取房源信息
				BgRoom room = rentRoomService.selectByPrimaryKey(order.getBgOrderRoomId());
				orderInfo.setRoomName(room.getBgRoomName());
				
				//获取预约数
				BgRoomInfo roomInfo = rentRoomInfoService.getRoomInfoByRoomId(order.getBgOrderRoomId());
				orderInfo.setRoomOrderNum(roomInfo.getBgRoomAppointNum() + "");
				
				orderInfo.setPrice(room.getBgRoomPrice() + "/" + getPriceUnit(room.getBgRoomPriceUnit()));
				orderInfo.setOrderStatus(order.getBgOrderStatus());
				
				String roomTips = StringUtils.EMPTY;
				List<DataDict> dataDicts = appDataDictValueService.queryDictValueList("房源标签");
				for(int j = 0; j < dataDicts.size(); j++) {
					String roomTipids = room.getBgRoomTips();
					if(StringUtils.isNotEmpty(roomTipids)) {
						if(roomTipids.contains(dataDicts.get(j).getDataDictId())) {
							roomTips = roomTips + dataDicts.get(j).getDataDictName() + ",";
						}
					}
				}
				
				if(StringUtils.isNotBlank(roomTips)) {
					roomTips = roomTips.substring(0, roomTips.length() - 1);
				}
				orderInfo.setRoomTips(roomTips);
				
				orderInfo.setAddressCountry(room.getBgRoomAddressCountry());
				orderInfo.setAddressCity(room.getBgRoomAddressCity());
				orderInfo.setAddressDetail(room.getBgRoomAddressDetail());
				orderInfo.setOrderNum(order.getBgOrderNumber());
				
				//获取用户信息
//				AppUser user = appUserService.getUserById(order.getBgOrderUserId());
				orderInfo.setOrderUser(order.getBgRenterName());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				if(order.getCreateTime() != null){
					String orderTime = sdf.format(order.getBgOrderCreateTime());
					orderInfo.setOrderTime(orderTime);
				}

				if(order.getBgOrderCheckinDate() != null){
					String checkTime = sdf.format(order.getBgOrderCheckinDate());
					orderInfo.setCheckInTime(checkTime);
				}

				resultData.setData(JSON.toJSONString(orderInfo));
				
			} else {
				resultData.setCode(Constant.RESULT_ORDER_NOT_EXISTS_CODE);
				resultData.setMsg(Constant.RESULT_ORDER_NOT_EXISTS_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		return JSON.toJSONString(resultData);
	}
	
	private String getPriceUnit(String unit) {
		if("0".equals(unit)) {
			return "周";
		} else if("1".equals(unit)) {
			return "月";
		} else if ("2".equals(unit)) {
			return "年";
		}
		return "";
	}
}
