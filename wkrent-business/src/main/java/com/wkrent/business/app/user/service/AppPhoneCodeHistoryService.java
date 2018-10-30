package com.wkrent.business.app.user.service;

import com.wkrent.common.entity.AppPhoneCodeHistory;

public interface AppPhoneCodeHistoryService {
	
	public void insertCodeHistory(AppPhoneCodeHistory codeHistory);
	
	public void validatePhoeCode(String historyId);
	
	public AppPhoneCodeHistory getPhoneCodeById(String historyId);

}
