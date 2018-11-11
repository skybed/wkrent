package com.wkrent.business.bg.modulemanagement.dao;

import com.wkrent.common.entity.po.BgModule;

/**
 * 模块设置
 * @author Administrator
 */
public interface BgModuleDao {

    /**
     * 新增模块设置
     * @param bgModule 模块设置
     */
    void insert(BgModule bgModule);

    /**
     * 更新模块设置
     * @param bgModule 模块设置
     */
    void update(BgModule bgModule);

    /**
     * 根据模块类型查找对应的模块信息
     * @param moduleType 模块类型
     * @return 符合条件模块设置
     */
    BgModule findModuleByType(String moduleType);

}
