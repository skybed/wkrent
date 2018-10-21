package com.wkrent.business.bguser.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.bguser.manager.dao.BgUserDao;
import com.wkrent.business.bguser.manager.service.BgUserService;
import com.wkrent.common.entity.BgUser;

@Service
public class BgUserServiceImpl implements BgUserService {
	
	@Autowired
	private BgUserDao bgUserDao;

	public List<BgUser> getAllUser() {
		return bgUserDao.getAllUserList();
	}

}
