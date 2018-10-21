/*
*
* BgDataDictValueMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgDataDictValue;

public interface BgDataDictValueMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgDataDictValueId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgDataDictValue record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgDataDictValue record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgDataDictValue selectByPrimaryKey(String bgDataDictValueId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgDataDictValue record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgDataDictValue record);
}