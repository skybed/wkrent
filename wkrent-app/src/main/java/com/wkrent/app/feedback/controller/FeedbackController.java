package com.wkrent.app.feedback.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.app.feedback.obj.FeedbackObj;
import com.wkrent.business.app.feedback.service.AppFeedbackService;
import com.wkrent.business.app.util.UUIDUtil;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.AppFeedback;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "suggestion", tags = "意见反馈信息")
@Controller
@RequestMapping("/app/api")
public class FeedbackController {
	
	@Autowired
	private AppFeedbackService appFeedbackService;
	
	/**
	  * 添加反馈信息
	 * @param request
	 * @param feedback
	 * @return
	 */
	@ApiOperation(value = "添加反馈信息", notes = "添加反馈信息", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/addFeedback.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addFeedback(HttpServletRequest request, @RequestBody FeedbackObj feedback) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		
		if(StringUtils.isNotEmpty(feedback.getFeedbackTitle()) && StringUtils.isNotEmpty(feedback.getFeedbackContent()) && StringUtils.isNotEmpty(feedback.getPhone())) {
			AppFeedback feedbackInfo = new AppFeedback();
			feedbackInfo.setAppFeedbackId(UUIDUtil.getUUIDString());
			feedbackInfo.setAppFeedbackTitle(feedback.getFeedbackTitle());
			feedbackInfo.setAppFeedbackContent(feedback.getFeedbackContent());
			feedbackInfo.setAppFeedbackContact(feedback.getPhone());
			feedbackInfo.setAppFeedbackTime(new Date());
			feedbackInfo.setCreateBy(feedback.getUserId());
			feedbackInfo.setCreateTime(new Date());
			feedbackInfo.setIsDelete("0");
			feedbackInfo.setUpdateBy(feedback.getUserId());
			feedbackInfo.setUpdateTime(new Date());
			appFeedbackService.insertFeedbackInfo(feedbackInfo);
			
			map.put("flag", true);
		} else {
			resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
			resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
		}
		
		resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
}
