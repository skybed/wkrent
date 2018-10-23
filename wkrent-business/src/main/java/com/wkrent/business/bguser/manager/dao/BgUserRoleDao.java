package com.wkrent.business.bguser.manager.dao;

import com.wkrent.common.entity.po.BgUserRole;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgUserRoleDao {

    /**
     * 根据用户id删除用户角色信息
     * @param bgUserRole 删除条件
     * @return 更新条数
     */
    int deleteByCondition(BgUserRole bgUserRole);

    /**
     * 新增用户角色信息
     * @param record 用户角色信息
     * @return 新增条数
     */
    int insert(BgUserRole record);

    /**
     * 根据条件查询用户角色信息
     * @param bgUserRole 查询条件
     * @return 符合条件未被删除记录
     */
    List<BgUserRole> findByCondition(BgUserRole bgUserRole);

}