package com.wkrent.business.bg.rolemanagement.service;

import com.wkrent.common.entity.po.BgRoleAuth;

/**
 * @author Administrator
 * 角色权限
 */
public interface BgRoleAuthService {

    /**
     * 新增角色权限信息
     * @param bgRoleAuth 角色权限信息
     * @return 新增条数
     */
    int insert(BgRoleAuth bgRoleAuth);

    /**
     * 根据角色id删除角色权限信息
     * @param roleId 角色id
     * @return 更新条数
     */
     int deleteByRoleId(String roleId);
}
