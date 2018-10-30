package com.wkrent.business.bg.rolemanagement.dao;

import com.wkrent.common.entity.po.BgRole;
import com.wkrent.common.entity.vo.BgRoleVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgRoleDao {


    /**
     * 根据角色名称查询角色信息
     * @param roleName 角色名称
     * @return 符合条件未删除角色信息
     */
    BgRole findRoleByName(String roleName);

    /**
     * 条件查询角色信息
     * @param roleVO 查询条件
     * @return 符合条件分页信息
     */
    List<BgRole> findByCondition(BgRoleVO roleVO);

    /**
     * 根据条件查询角色总条数
     * @param roleVO 查询条件
     * @return 总条数
     */
    int countByCondition(BgRoleVO roleVO);

    /**
     * 新增平台角色信息
     * @param bgRole 新增角色
     * @return 新增条数
     */
    int insertRole(BgRole bgRole);

    /**
     * 修改平台角色信息
     * @param bgRole 更新角色
     * @return 更新条数
     */
    int update(BgRole bgRole);

    /**
     * 根据id查询角色信息
     * @param roleId 角色id
     * @return 符合条件未被删除角色信息
     */
    BgRole selectByPrimaryKey(String roleId);

    /**
     * 启用/禁用角色
     * @param bgRole 角色信息
     * @return 成功条数
     */
    int updateStatus(BgRole bgRole);

    /**
     * 删除角色信息
     * @param bgRole 角色信息
     * @return 成功条数
     */
    int delete(BgRole bgRole);
}
