/*
*
* BgRoleAuthMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgRoleAuth;

public interface BgRoleAuthMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgRoleAuthId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgRoleAuth record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgRoleAuth record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgRoleAuth selectByPrimaryKey(String bgRoleAuthId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgRoleAuth record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgRoleAuth record);
}