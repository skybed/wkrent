package com.wkrent.app.base.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.app.util.UUIDUtil;
import com.wkrent.business.app.base.obj.RegionInfo;
import com.wkrent.business.app.base.obj.RegionItem;
import com.wkrent.business.app.base.service.AppRegionService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.AppRegion;
import com.wkrent.common.obj.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "regioninfo", tags = "国家地区信息")
@Controller
@RequestMapping("/app/api")
public class RegionController {
	
	@Autowired
	private AppRegionService appRegionService;
	
	@RequestMapping(value = "/initRegionInfo.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String initRegionInfo(HttpServletRequest request) {
		
		File file = new File("/sts_space/wkrent/wkrent-app/src/main/resources/region.txt");  
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String regionName = tempString.split("\\*\\*")[0];
                String regionCnName = new String(tempString.split("\\*\\*")[1].getBytes(), "UTF-8");
                String regionMark = tempString.split("\\*\\*")[2];
                String regionCode = tempString.split("\\*\\*")[3];
                
                AppRegion appRegion = new AppRegion();
                appRegion.setRegionId(UUIDUtil.getUUIDString());
                appRegion.setRegionName(regionName);
                appRegion.setCreateBy("admin");
                appRegion.setCreateTime(new Date());
                appRegion.setIsDelete("0");
                appRegion.setRegionCnName(regionCnName);
                appRegion.setRegionCode(regionCode);
                appRegion.setDescription("");
                appRegion.setRegionMark(regionMark);
                appRegion.setUpdateBy("admin");
                appRegion.setUpdateTime(new Date());
                
                appRegionService.insertAppRegin(appRegion);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		
		return "";
	}
	
	/**
	  * 获取所有地区信息
	 * @param request
	 * @param regionName
	 * @param regionCnName
	 * @param regionCode
	 * @return
	 */
	@ApiOperation(value = "获取所有地区信息", notes = "获取所有地区信息", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getCountryAndCodeList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCountryAndCodeList(HttpServletRequest request, String regionInfo) {
		List<RegionInfo> regionInfos = appRegionService.getAllRegionList(regionInfo);
		
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		resultData.setData(JSON.toJSONString(regionInfos));
		return JSON.toJSONString(resultData);
	}
	
	
	/**
	  * 按照字母分类获取所有地区信息
	 * @param request
	 * @param regionName
	 * @param regionCnName
	 * @param regionCode
	 * @return
	 */
	@ApiOperation(value = "按照字母分类获取所有地区信息", notes = "获取所有地区信息", httpMethod = "POST", response = String.class)
	@RequestMapping(value = "/getCountryListByCharacter.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCountryListByCharacter(HttpServletRequest request, String regionInfo) {
		List<RegionInfo> regionInfos = appRegionService.getAllRegionList(regionInfo);
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for(int i = 0; i < regionInfos.size(); i++) {
			List<String> list = new ArrayList<String>();
			String character = regionInfos.get(i).getRegionName().substring(0, 1).toUpperCase();
			if(!map.containsKey(character)) {
				list.add(regionInfos.get(i).getRegionName());
				map.put(character, list);
			} else {
				map.get(character).add(regionInfos.get(i).getRegionName());
			}
		}
		
		List<RegionItem> items = new ArrayList<RegionItem>();
		
		//遍历Map返回结果
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			List<String> result = entry.getValue();
			Collections.reverse(result);
			RegionItem regionItem = new RegionItem(entry.getKey(), result);
			items.add(regionItem);
		}
		
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		resultData.setData(JSON.toJSONString(items));
		return JSON.toJSONString(resultData);
	}
}
