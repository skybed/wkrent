package com.wkrent.business.app.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.user.dao.AppPhoneCodeHistoryDao;
import com.wkrent.business.app.user.service.AppPhoneCodeHistoryService;
import com.wkrent.common.entity.AppPhoneCodeHistory;

@Service
public class AppPhoneCodeHistoryServiceImpl implements AppPhoneCodeHistoryService {
	
	@Autowired
	private AppPhoneCodeHistoryDao appPhoneCodeHistoryDao;

	@Override
	public void insertCodeHistory(AppPhoneCodeHistory codeHistory) {
		appPhoneCodeHistoryDao.insertSelective(codeHistory);
	}

	@Override
	public void validatePhoeCode(String historyId) {
		appPhoneCodeHistoryDao.validatePhoneCode(historyId);
	}

	@Override
	public AppPhoneCodeHistory getPhoneCodeById(String historyId) {
		return appPhoneCodeHistoryDao.selectByPrimaryKey(historyId);
	}

}
