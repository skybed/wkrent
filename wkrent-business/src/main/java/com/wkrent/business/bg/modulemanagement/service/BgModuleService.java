package com.wkrent.business.bg.modulemanagement.service;

import com.wkrent.common.entity.vo.BgModuleVO;

/**
 * @author Administrator
 */
public interface BgModuleService {

    /**
     * 保存模块设置
     * @param moduleVO 模块设置信息
     * @param loginAccount 登录账号
     */
    void save(BgModuleVO moduleVO, String loginAccount);

    /**
     * 根据类型查询模块设置信息
     * @param moduleType 模块类型
     * @return 符合条件模块设置
     */
    BgModuleVO findByType(String moduleType);

}
