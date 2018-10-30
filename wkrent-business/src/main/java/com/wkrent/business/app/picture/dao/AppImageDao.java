package com.wkrent.business.app.picture.dao;

import java.util.List;

import com.wkrent.common.entity.BgPicAttach;

public interface AppImageDao {
	
	public List<BgPicAttach> selectByOwnerId(String ownerId);
	
	public void insertSelective(BgPicAttach bgPicAttach);

}
