package com.wkrent.app.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.app.base.obj.DataDict;
import com.wkrent.business.app.base.service.AppDataDictValueService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "datadict", tags = "数据字典")
@Controller
@RequestMapping("/app/api")
public class DataDictController {

	@Autowired
	private AppDataDictValueService appDataDictValueService;
	
	/**
	  * 获取指定type数据类型
	 * @param request
	 * @param regionName
	 * @param regionCnName
	 * @param regionCode
	 * @return
	 */
	@ApiOperation(value = "获取指定type数据类型", notes = "获取指定type数据类型", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getDataDictList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDataDictList(HttpServletRequest request, String type) {
		List<DataDict> dataDicts = appDataDictValueService.queryDictValueList(type);
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		resultData.setData(JSON.toJSONString(dataDicts));
		return JSON.toJSONString(resultData);
	}
}
