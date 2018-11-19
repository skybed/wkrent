package com.wkrent.business.bg.area.dao;

import com.wkrent.common.entity.po.BgArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 国家地区
 * @author Administrator
 */
public interface BgAreaDao {


    /**
     * 根据等级查询地区信息
     * @param level
     * @return
     */
    List<BgArea> queryAreaByLevel(@Param("level") int level);

    /**
     * 根据父级地区code查询地区信息
     * @param areaParentCode 父级地区code
     * @return 符合条件地区信息
     */
    List<BgArea> queryAreaByParentCode(@Param("areaParentCode") String areaParentCode);
}