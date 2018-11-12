package com.wkrent.app.attention.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.app.util.UUIDUtil;
import com.wkrent.business.app.rent.service.AppAttentionService;
import com.wkrent.business.app.rent.service.RentRoomService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.AppAttention;
import com.wkrent.common.entity.BgRoom;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "favourite", tags = "我的收藏")
@Controller
@RequestMapping("/app/api")
public class AttentionController {
	
	@Autowired
	private AppAttentionService appAttentionService;
	
	@Autowired
	private RentRoomService rentRoomService;
	
	/**
	  * 获取我的收藏分页信息
	 * @param request
	 * @param userId
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "获取我的收藏分页信息", notes = "获取我的收藏分页信息", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getMyAttentListByPager.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMyAttentListByPager(HttpServletRequest request, String userId, Integer currentIndex, Integer pageSize) {
		
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(userId)) {
			Integer count = appAttentionService.countAppAttention(userId);
			List<AppAttention> appAttentions = appAttentionService.getAppAttentionByPager(userId, currentIndex, pageSize);
			
			map.put("count", count);
			map.put("list", appAttentions);
			map.put("flag", true);
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
	/**
	  * 收藏/取消房源
	 * @param request
	 * @param userId
	 * @param roomId
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "收藏/取消房源 0-取消 1-收藏", notes = "收藏/取消房源 0-取消 1-收藏", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/rentRoomAttent.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String rentRoomAttent(HttpServletRequest request, String userId, String roomId, String type) {
		
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(roomId)) {
			
			if("0".equals(type)) {
				//判断该房源是否已经被收藏
				AppAttention attention = appAttentionService.selectByRoomAndUserId(roomId, userId);
				if(attention != null) {//被收藏 取消收藏
					appAttentionService.deleteAppAttention(attention.getAppAttentionId(), userId);
					map.put("flag", true);
				} else {//未收藏，无法取消
					resultData.setCode(Constant.RESULT_RENTROOM_NOT_ATTENTION_CODE);
					resultData.setMsg(Constant.RESULT_RENTROOM_NOT_ATTENTION_MSG);
				}
			} else if("1".equals(type)) {
				//判断该房源是否已经收藏
				AppAttention attention = appAttentionService.selectByRoomAndUserId(roomId, userId);
				if(attention == null) {//未被收藏，收藏
					BgRoom room = rentRoomService.selectByPrimaryKey(roomId);
					if(room != null) {
						AppAttention appAttention = new AppAttention();
						appAttention.setAppAttentionId(UUIDUtil.getUUIDString());
						appAttention.setAppRoomId(roomId);
						appAttention.setAppUserId(userId);
						appAttention.setCreateBy(userId);
						appAttention.setCreateTime(new Date());
						appAttention.setIsDelete("0");
						appAttention.setUpdateBy(userId);
						appAttention.setUpdateTime(new Date());
						appAttentionService.saveAppAttention(appAttention);
						
						map.put("flag", true);
					} else {
						resultData.setCode(Constant.RESULT_RENTROOM_NOT_EXISTS_CODE);
						resultData.setMsg(Constant.RESULT_RENTROOM_NOT_EXISTS_MSG);
					}
				} else {//已被收藏，直接返回
					resultData.setCode(Constant.RESULT_RENTROOM_ALREADY_ATTENTION_CODE);
					resultData.setMsg(Constant.RESULT_RENTROOM_ALREADY_ATTENTION_MSG);
				}
			} else {
				resultData.setCode(Constant.RESULT_REQUIRE_PARAM_INVALIDATE_CODE);
				resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_INVALIDATE_MSG);
			}
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}

}
