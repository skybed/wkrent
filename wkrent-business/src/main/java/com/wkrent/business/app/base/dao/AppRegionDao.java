/*
*
* AppRegionMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-24
*/
package com.wkrent.business.app.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wkrent.common.entity.AppRegion;

public interface AppRegionDao {
    /**
     *
     * 通过id删除实体
     */
    int deleteByPrimaryKey(String regionId);

    /**
     *
     * 新增实体 字段不判空
     */
    int insert(AppRegion record);

    /**
     *
     * 新增实体 为空字段不处理
     */
    int insertSelective(AppRegion record);

    /**
     *
     * 通过id获取对象
     */
    AppRegion selectByPrimaryKey(String regionId);

    /**
     *
     * 通过id更新对象 为空字段不处理
     */
    int updateByPrimaryKeySelective(AppRegion record);

    /**
     *
     * 通过id更新对象 字段不判空
     */
    int updateByPrimaryKey(AppRegion record);
    
    /**
     * 获取所有地区列表
     * @return
     */
    List<AppRegion> getAllRegionList(@Param("regionInfo")String regionInfo);
}