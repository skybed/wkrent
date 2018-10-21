/*
*
* BgDataDictMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgDataDict;

public interface BgDataDictMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgDataDictId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgDataDict record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgDataDict record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgDataDict selectByPrimaryKey(String bgDataDictId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgDataDict record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgDataDict record);
}