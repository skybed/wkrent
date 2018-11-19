package com.wkrent.business.bg.area.service;

import com.wkrent.common.entity.base.BaseAjaxVO;

/**
 * 国家地区
 * @author Administrator
 */
public interface BgAreaService {

    /**
     * 查询国家信息
     * @return 符合条件国家信息
     */
    BaseAjaxVO queryCountryInfo();

    /**
     * 根据上级code查询地区信息
     * @param parentCode 上级code
     * @return 符合条件地区信息
     */
    BaseAjaxVO findByParentCode(String parentCode);
}
