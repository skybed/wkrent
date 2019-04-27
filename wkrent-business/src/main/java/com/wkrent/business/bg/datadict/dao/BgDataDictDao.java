package com.wkrent.business.bg.datadict.dao;

import com.wkrent.common.entity.po.BgDataDict;

/**
 * 数据字典类型
 * @author Administrator
 */
public interface BgDataDictDao {

    /**
     * 根据数据字典类型查询
     * @param dictType 数据字典类型
     * @return 符合条件未删除的数据字典（主表）
     */
    BgDataDict findByType(String dictType);

    /**
     * 新增数据字典类型
     * @param record 数据字典枚举值信息
     * @return 新增条数
     *
     */
    int insert(BgDataDict record);
}