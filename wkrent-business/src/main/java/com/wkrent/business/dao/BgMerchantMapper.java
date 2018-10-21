/*
*
* BgMerchantMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.dao;

import com.wkrent.common.entity.BgMerchant;

public interface BgMerchantMapper {
    /**
     *
     * @mbg.generated 2018-10-21
     */
    int deleteByPrimaryKey(String bgMerchantId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insert(BgMerchant record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgMerchant record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgMerchant selectByPrimaryKey(String bgMerchantId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgMerchant record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgMerchant record);
}