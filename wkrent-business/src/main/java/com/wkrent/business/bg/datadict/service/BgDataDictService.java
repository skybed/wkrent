package com.wkrent.business.bg.datadict.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.vo.BgDataDictVO;

/**
 * 数据字典类型
 * @author Administrator
 */
public interface BgDataDictService {

    /**
     * 新增枚举类型信息
     * @param dataDictVO 数据字典类型
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgDataDictVO dataDictVO, String loginAccount);
}
