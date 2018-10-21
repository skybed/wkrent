/*
*
* BgMenuMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgMenu;

public interface BgMenuMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgMemuId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgMenu record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgMenu record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgMenu selectByPrimaryKey(String bgMemuId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgMenu record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgMenu record);
}