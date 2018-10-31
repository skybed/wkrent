package com.wkrent.business.app.picture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wkrent.common.entity.BgPicAttach;

public interface AppImageDao {
	
	public List<BgPicAttach> selectByOwnerId(String ownerId);
	
	public void insertSelective(BgPicAttach bgPicAttach);

	BgPicAttach selectByPrimaryKey(String picAttachId);
	
	public void deletePicAttach(String picId);
	
	public void updatePicAttachOwner(@Param("picId")String picId, @Param("ownerId")String ownerId);
}
