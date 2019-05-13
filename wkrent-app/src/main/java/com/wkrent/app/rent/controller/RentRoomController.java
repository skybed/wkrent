package com.wkrent.app.rent.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.app.util.RandomNumUtil;
import com.wkrent.app.util.UUIDUtil;
import com.wkrent.business.app.base.obj.DataDict;
import com.wkrent.business.app.base.service.AppDataDictValueService;
import com.wkrent.business.app.order.service.AppOrderService;
import com.wkrent.business.app.picture.service.AppImageService;
import com.wkrent.business.app.rent.obj.ApponitInfo;
import com.wkrent.business.app.rent.obj.RentRoomCondition;
import com.wkrent.business.app.rent.obj.RoomDetailInfo;
import com.wkrent.business.app.rent.obj.RoomInfo;
import com.wkrent.business.app.rent.service.AppAttentionService;
import com.wkrent.business.app.rent.service.RentRoomInfoService;
import com.wkrent.business.app.rent.service.RentRoomService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.AppAttention;
import com.wkrent.common.entity.BgOrder;
import com.wkrent.common.entity.BgPicAttach;
import com.wkrent.common.entity.BgRoom;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "rentroom", tags = "房源信息")
@Controller
@RequestMapping("/app/api")
public class RentRoomController {
	
	@Autowired
	private RentRoomService rentRoomService;
	
	@Autowired
	private RentRoomInfoService rentRoomInfoService;
	
	@Autowired
	private AppImageService appImageService;
	
	@Autowired
	private AppAttentionService appAttentionService;
	
	@Autowired
	private AppOrderService appOrderService;
	
	@Autowired
	private AppDataDictValueService appDataDictValueService;
	
	/**
	  * 获取房源列表信息
	 * @param request
	 * @param condition
	 * @return
	 */
	@ApiOperation(value = "获取房源列表信息", notes = "获取房源列表信息", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getRentRoomListByPager.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getRentRoomListByPager(HttpServletRequest request, RentRoomCondition condition) {
		
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//获取登陆用户信息
		String userId = "";
		if(request.getSession().getAttribute("current_user_id") != null && StringUtils.isNotEmpty(request.getSession().getAttribute("current_user_id").toString())) {
			userId = request.getSession().getAttribute("current_user_id").toString();
			condition.setUserId(userId);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Integer count = rentRoomService.getRentRoomCount(condition);
		List<RoomInfo> bgRooms = rentRoomService.getRentRoomListByPager(condition);
		map.put("count", count);
		map.put("list", bgRooms);
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	  * 获取房源详情
	 * @param request
	 * @param roomId
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "获取房源详情", notes = "获取房源详情", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getRentRoomDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getRentRoomDetail(HttpServletRequest request, String roomId) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//获取登陆用户信息
		String userId = "";
		if(request.getSession().getAttribute("current_user_id") != null && StringUtils.isNotEmpty(request.getSession().getAttribute("current_user_id").toString())) {
			userId = request.getSession().getAttribute("current_user_id").toString();
		}
		
		if(StringUtils.isNotEmpty(roomId)) {
			BgRoom room = rentRoomService.selectByPrimaryKey(roomId);
			if(room != null) {
				RoomDetailInfo detailInfo = new RoomDetailInfo();
				detailInfo.setRoomId(roomId);
				
				List<String> picList = new ArrayList<String>();
				//获取房源附件
				List<BgPicAttach> attachs = appImageService.selectByOwnerId(roomId);
				if(CollectionUtils.isNotEmpty(attachs)) {
					for(int i = 0; i < attachs.size(); i++) {
						picList.add(attachs.get(i).getPicAttachId());
					}
				}
				detailInfo.setPics(picList);
				
				detailInfo.setRoomName(room.getBgRoomName());
				detailInfo.setPrice(room.getBgRoomPrice() + "/" + getPriceUnit(room.getBgRoomPriceUnit()));
				detailInfo.setStatus(room.getBgRoomStatus());
				
				String roomTips = "";
				List<DataDict> dataDicts = appDataDictValueService.queryDictValueList("房源标签");
				for(int j = 0; j < dataDicts.size(); j++) {
					String roomTipids = room.getBgRoomTips();
					if(StringUtils.isNotEmpty(roomTipids)) {
						if(roomTipids.contains(dataDicts.get(j).getDataDictId())) {
							roomTips = roomTips + dataDicts.get(j).getDataDictName() + ",";
						}
					}
				}
				
				if(roomTips.length() > 0) {
					roomTips = roomTips.substring(0, roomTips.length() - 1);
				}
				
				detailInfo.setRoomTips(roomTips);
				
				detailInfo.setAddressCountry(room.getBgRoomAddressCountry());
				detailInfo.setAddressCity(room.getBgRoomAddressCity());
				detailInfo.setAddressProvince(room.getBgRoomAddressProvince());
				detailInfo.setAddressDetail(room.getBgRoomAddressDetail());
				detailInfo.setAddressX(room.getBgRoomAddressX() + "");
				detailInfo.setAddressY(room.getBgRoomAddressY() + "");
				detailInfo.setRoomDetail(room.getBgRoomDetail());
				
				//判断该房源是否已经收藏
				AppAttention attention = appAttentionService.selectByRoomAndUserId(roomId, userId);
				if(attention != null) {
					detailInfo.setIsAttention("1");
				} else {
					detailInfo.setIsAttention("0");
				}
				
				resultData.setData(JSON.toJSONString(detailInfo));
				
				//将房源浏览量+1
				rentRoomInfoService.addViewNum(roomId);
			} else {
				resultData.setCode(Constant.RESULT_RENTROOM_NOT_EXISTS_CODE);
				resultData.setMsg(Constant.RESULT_RENTROOM_NOT_EXISTS_MSG);
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
	
	/**
	  * 预约房源
	 * @param request
	 * @param apponitInfo
	 * @return
	 */
	@ApiOperation(value = "预约房源", notes = "预约房源", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/appointRentRoom.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String appointRentRoom(HttpServletRequest request, ApponitInfo apponitInfo) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		//获取登陆用户信息
		String userId = "";
		if(request.getSession().getAttribute("current_user_id") != null && StringUtils.isNotEmpty(request.getSession().getAttribute("current_user_id").toString())) {
			userId = request.getSession().getAttribute("current_user_id").toString();
			apponitInfo.setUserId(userId);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(apponitInfo.getRoomId())) {
			BgRoom room = rentRoomService.selectByPrimaryKey(apponitInfo.getRoomId());
			if(room != null) {
				//判断房源是否可以预约
				if("1".equals(room.getBgRoomStatus())) {
					
					//判断是否重复预约
					BgOrder order = appOrderService.selectByRoomAndUserId(apponitInfo.getRoomId(), apponitInfo.getUserId());
					if(order != null) {
						resultData.setCode(Constant.RESULT_RENTROOM_APPOINT_ALREADY_CODE);
						resultData.setMsg(Constant.RESULT_RENTROOM_APPOINT_ALREADY_MSG);
					} else {
						//修改房源状态
						rentRoomService.updateRoomStatus(apponitInfo.getRoomId(), apponitInfo.getUserId());
						
						//修改房源预约数信息
						boolean flag = rentRoomInfoService.addAppoint(apponitInfo.getRoomId(), apponitInfo.getUserId());
						
						//预约成功
						if(flag) {
							//生成订单信息
							order = new BgOrder();
							order.setBgOrderId(UUIDUtil.getUUIDString());
							order.setBgOrderNumber(RandomNumUtil.getRandomNum());
							
							try {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date utilDate = sdf.parse(apponitInfo.getCheckInTime());
								order.setBgOrderCheckinDate(utilDate);
							} catch (ParseException e) {
								Logger.getRootLogger().error("appointRentRoom checkInTime format exception:" + e.getMessage(), e);
							}
							
							order.setBgOrderCreateTime(new Date());
							order.setBgOrderRentTenancy(room.getBgRoomPriceUnit());
							order.setBgOrderRentUnit("0");
							order.setBgOrderRoomId(apponitInfo.getRoomId());
							order.setBgOrderStatus("0");
							order.setBgOrderUserId(apponitInfo.getUserId());
							order.setCreateBy(apponitInfo.getUserId());
							order.setCreateTime(new Date());
							order.setIsDelete("0");
							order.setUpdateBy(apponitInfo.getUserId());
							order.setUpdateTime(new Date());
							order.setBgRenterSchool(apponitInfo.getSchool());
							order.setBgRenterName(apponitInfo.getUserName());
							order.setBgRenterEmail(apponitInfo.getEmail());
							order.setBgRenterPhone(apponitInfo.getPhone());
							
							appOrderService.insertOrder(order);
							
							map.put("flag", true);
						} else {
							resultData.setCode(Constant.RESULT_RENTROOM_APPOINT_FULL_CODE);
							resultData.setMsg(Constant.RESULT_RENTROOM_APPOINT_FULL_MSG);
						}
					}
				} else {
					resultData.setCode(Constant.RESULT_RENTROOM_NOT_APPOINT_CODE);
					resultData.setMsg(Constant.RESULT_RENTROOM_NOT_APPOINT_MSG);
				}
			} else {
				resultData.setCode(Constant.RESULT_RENTROOM_NOT_EXISTS_CODE);
				resultData.setMsg(Constant.RESULT_RENTROOM_NOT_EXISTS_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
}
