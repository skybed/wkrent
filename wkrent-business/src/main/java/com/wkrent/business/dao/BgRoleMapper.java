/*
*
* BgRoleMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgRole;

public interface BgRoleMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgRoleId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgRole record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgRole record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgRole selectByPrimaryKey(String bgRoleId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgRole record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgRole record);
}