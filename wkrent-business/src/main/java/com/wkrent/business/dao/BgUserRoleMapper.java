/*
*
* BgUserRoleMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgUserRole;

public interface BgUserRoleMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgUserRoleId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgUserRole record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgUserRole record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgUserRole selectByPrimaryKey(String bgUserRoleId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgUserRole record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgUserRole record);
}