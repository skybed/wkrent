/*
*
* BgOrderMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgOrder;

public interface BgOrderMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgOrderId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgOrder record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgOrder record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgOrder selectByPrimaryKey(String bgOrderId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgOrder record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgOrder record);
}