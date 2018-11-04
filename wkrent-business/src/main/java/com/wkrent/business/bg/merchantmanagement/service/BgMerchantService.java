package com.wkrent.business.bg.merchantmanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgMerchantVO;

/**
 * @author Administrator
 */
public interface BgMerchantService {

    /**
     * 分页查询商家信息
     * @param merchantVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgMerchantVO> findByCondition(BgMerchantVO merchantVO);

    /**
     * 新增商家信息
     * @param merchantVO 商家信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgMerchantVO merchantVO, String loginAccount);

    /**
     * 根据商家id修改商家信息
     * @param merchantVO 待修改商家信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    void update(BgMerchantVO merchantVO, String loginAccount);

    /**
     * 根据商家id查询商家详情信息
     * @param merchantId 商家id
     * @return 符合条件未删除商家信息
     */
    BaseAjaxVO findByMerchantId(String merchantId);

    /**
     * 根据id删除商家
     * @param merchantId 商家id
     * @param loginAccount 当前登录账号
     */
    void delete(String merchantId, String loginAccount);

    /**
     * 根据启用商家
     * @param merchantId 商家id
     * @param loginAccount 当前登录账号
     */
    void enable(String merchantId, String loginAccount);

    /**
     * 根据id停用商家
     * @param merchantId 商家id
     * @param loginAccount 当前登录账号
     */
    void disable(String merchantId, String loginAccount);


}
