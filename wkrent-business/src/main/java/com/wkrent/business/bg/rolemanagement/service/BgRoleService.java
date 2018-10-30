package com.wkrent.business.bg.rolemanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgRoleVO;

public interface BgRoleService {


    /**
     * 分页查询角色信息
     * @param roleVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgRoleVO> findByCondition(BgRoleVO roleVO);

    /**
     * 新增角色信息
     * @param roleVO 角色信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgRoleVO roleVO, String loginAccount);

    /**
     * 根据角色账号修改角色信息
     * @param roleVO 待修改角色信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    void update(BgRoleVO roleVO, String loginAccount);

    /**
     * 根据id删除角色
     * @param roleId 角色id
     * @param loginAccount 当前登录账号
     */
    void delete(String roleId, String loginAccount);

    /**
     * 根据id禁用角色
     * @param roleId 角色id
     * @param loginAccount 当前登录账号
     */
    void disable(String roleId, String loginAccount);

    /**
     * 根据id启用角色
     * @param roleId 角色id
     * @param loginAccount 当前登录账号
     */
    void enable(String roleId, String loginAccount);

}
