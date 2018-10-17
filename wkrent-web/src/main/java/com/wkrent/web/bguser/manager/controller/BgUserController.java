package com.wkrent.web.bguser.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.bguser.manager.service.BgUserService;

@Controller
@RequestMapping("/bguser")
public class BgUserController {
	
	@Autowired
	private BgUserService bgUserService;
	
	@RequestMapping(value = "/getUsers.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllUser(HttpServletRequest request) {
		return JSON.toJSONString(bgUserService.getAllUser());
	}

}
