/*
*
* BgMerchantMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.bg.merchantmanagement.dao;

import com.wkrent.common.entity.po.BgMerchant;
import com.wkrent.common.entity.vo.BgMerchantVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgMerchantDao {

    /**
     * 根据id删除商家信息
     * @param bgMerchant 商家信息
     * @return 更新条数
     */
    int deleteByPrimaryKey(BgMerchant bgMerchant);

    /**
     * 新增商家信息
     * @param record 待新增商家信息
     * @return 新增条数
     */
    int insert(BgMerchant record);

    /**
     * 根据id 查询商家信息
     * @param bgMerchantId 商家id
     * @return 符合条件未删除商家信息
     */
    BgMerchant selectByPrimaryKey(String bgMerchantId);


    /**
     * 条件查询商家信息
     * @param bgMerchantVO 查询条件
     * @return 符合条件结果
     */
    List<BgMerchant> findByCondition(BgMerchantVO bgMerchantVO);

    /**
     * 根据条件查询商家总条数
     * @param bgMerchantVO 查询条件
     * @return 总条数
     */
    int countByCondition(BgMerchantVO bgMerchantVO);

    /**
     * 修改商家信息
     * @param bgMerchant 更新商家信息
     * @return 更新条数
     */
    int update(BgMerchant bgMerchant);

    /**
     * 更新商家 启用 状态
     * @param bgMerchant 商家信息
     * @return 成功条数
     */
    int updateStatus(BgMerchant bgMerchant);

    /**
     * 根据名称查询未删除商家信息
     * @param merchantName 商家名称
     * @return 未删除商家信息
     */
    BgMerchant findByName(String merchantName);
}