/*
*
* BgRoomInfoMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.bg.roommanagement.dao;

import com.wkrent.common.entity.po.BgRoomInfo;

public interface BgRoomInfoDao {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgRoomInfoId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgRoomInfo record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgRoomInfo record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgRoomInfo selectByPrimaryKey(String bgRoomInfoId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgRoomInfo record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgRoomInfo record);
}