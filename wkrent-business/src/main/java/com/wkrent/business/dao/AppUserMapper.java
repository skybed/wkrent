/*
*
* AppUserMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.AppUser;

public interface AppUserMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String appUserId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(AppUser record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(AppUser record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    AppUser selectByPrimaryKey(String appUserId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(AppUser record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(AppUser record);
}