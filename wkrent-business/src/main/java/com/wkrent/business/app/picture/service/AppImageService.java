package com.wkrent.business.app.picture.service;

import java.util.List;

import com.wkrent.common.entity.BgPicAttach;

public interface AppImageService {
	
	public List<BgPicAttach> selectByOwnerId(String ownerId);

	public void savePicAttach(BgPicAttach bgPicAttach);
	
	public BgPicAttach selectById(String picId);
	
	public void deletePicAttach(String picId);
	
	public void deletePicAttachByOwnerId(String ownerId);
	
	public void updatePicAttachOwner(String picId, String ownerId);
}
