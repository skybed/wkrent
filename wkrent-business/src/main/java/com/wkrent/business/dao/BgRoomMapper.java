/*
*
* BgRoomMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgRoom;

public interface BgRoomMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgRoomId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgRoom record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgRoom record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgRoom selectByPrimaryKey(String bgRoomId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgRoom record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgRoom record);
}