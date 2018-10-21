/*
*
* BgPicAttachMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgPicAttach;

public interface BgPicAttachMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String picAttachId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgPicAttach record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgPicAttach record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgPicAttach selectByPrimaryKey(String picAttachId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgPicAttach record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgPicAttach record);
}