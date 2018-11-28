package com.wkrent.business.bg.rolemanagement.service;

import com.wkrent.common.entity.po.BgRoleAuth;

import java.util.List;

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

    /**
     * 根据角色idList查询权限信息
     * @param roleIdList 角色idList
     * @return 角色权限信息
     */
    List<BgRoleAuth> queryByRoleIdList(List<String> roleIdList);
}
