/*
*
* BgRoleAuthMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.bg.rolemanagement.dao;

import com.wkrent.common.entity.po.BgRoleAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgRoleAuthDao {

    /**
     * 新增角色权限
     * @param record 角色权限信息
     * @return 新增条数
     */
    int insert(BgRoleAuth record);

    /**
     * 根据角色id删除角色权限信息
     * @param roleId 角色id
     * @return 删除条数
     */
    int deleteByRoleId(@Param("bgRoleId") String roleId);

    /**
     * 根据角色idList查询权限信息
     * @param roleIdList 角色idList
     * @return 角色权限信息
     */
    List<BgRoleAuth> queryByRoleIdList(@Param("roleIdList") List<String> roleIdList);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int insertSelective(BgRoleAuth record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    BgRoleAuth selectByPrimaryKey(String bgRoleAuthId);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKeySelective(BgRoleAuth record);

    /**
     *
     * @mbg.generated 2018-10-21
     */
    int updateByPrimaryKey(BgRoleAuth record);
}