/*
*
* BgUserMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgUser;

public interface BgUserMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgUserId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgUser record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgUser record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgUser selectByPrimaryKey(String bgUserId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgUser record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgUser record);
}