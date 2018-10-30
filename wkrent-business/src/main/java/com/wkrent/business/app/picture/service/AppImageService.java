package com.wkrent.business.app.picture.service;

import java.util.List;

import com.wkrent.common.entity.BgPicAttach;

public interface AppImageService {
	
	public List<BgPicAttach> selectByOwnerId(String ownerId);

	public void savePicAttach(BgPicAttach bgPicAttach);
	
}
