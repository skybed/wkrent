package com.wkrent.business.bg.usermanagement.service;

import com.wkrent.common.entity.po.BgUserRole;
import com.wkrent.common.entity.vo.BgUserRoleVO;

import java.util.List;

/**
 * @author Administrator
 * 角色权限
 */
public interface BgUserRoleService {

    /**
     * 新增用户角色信息
     * @param bgUserRole 角色信息
     * @return 新增条数
     */
    int insert(BgUserRole bgUserRole);

    /**
     * 根据用户id删除用户角色角色信息
     * @param userId 角色id
     * @return 更新条数
     */
     int deleteByUserId(String userId);

    /**
     * 根据条件查询用户角色信息
     * @param bgUserRole 查询条件
     * @return 符合条件未被删除记录
     */
    List<BgUserRoleVO> findByCondition(BgUserRole bgUserRole);
}
