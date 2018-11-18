package com.wkrent.business.app.base.service;

import java.util.List;

import com.wkrent.business.app.base.obj.DataDict;

public interface AppDataDictValueService {

    /**
       * 查询枚举值List
     * @param dictType 查询条件
     * @return 符合条件枚举值信息
     */
    List<DataDict> queryDictValueList(String dictType);

}
