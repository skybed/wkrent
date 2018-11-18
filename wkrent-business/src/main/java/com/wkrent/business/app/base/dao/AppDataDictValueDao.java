package com.wkrent.business.app.base.dao;

import java.util.List;

import com.wkrent.common.entity.vo.BgDataDictValueVO;

public interface AppDataDictValueDao {

    /**
     * 根据枚举类型查询生效未被删除枚举值信息
     * @param dataDictType 枚举类型
     * @return 符合条件信息
     */
    List<BgDataDictValueVO> queryDictValueByType(String dataDictType);
    
}