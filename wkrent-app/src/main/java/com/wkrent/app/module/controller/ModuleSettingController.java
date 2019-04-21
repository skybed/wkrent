package com.wkrent.app.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.bg.modulemanagement.service.BgModuleService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.enums.ModuleTypeEnum;
import com.wkrent.common.entity.vo.BgModuleVO;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "module", tags = "服务协议")
@Controller
@RequestMapping("/app/api")
public class ModuleSettingController extends BaseController{

    @Autowired
    private BgModuleService bgModuleService;

    @ApiOperation(value = "查看服务协议", notes = "查看服务协议", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/queryAgreement.do", method = RequestMethod.POST)
    @ResponseBody
    public String queryAgreement() {
    	ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		BgModuleVO moduleVO = bgModuleService.findByType(ModuleTypeEnum.AGREEMENT.getCode());
		resultData.setData(JSON.toJSONString(moduleVO));
		return JSON.toJSONString(resultData);
    }

    @ApiOperation(value = "查看租房说明", notes = "查看租房说明", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/queryRentExplain.do", method = RequestMethod.POST)
    @ResponseBody
    public String queryRentExplain(){
    	ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
        BgModuleVO moduleVO = bgModuleService.findByType(ModuleTypeEnum.RENT_EXPLAIN.getCode());
        resultData.setData(JSON.toJSONString(moduleVO));
        
		return JSON.toJSONString(resultData);
    }

}
