/*
*
* AppUserMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.bg.appusermanagement.dao;

import com.wkrent.common.entity.po.AppUser;
import com.wkrent.common.entity.vo.AppUserVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgAppUserDao {

    /**
     * 条件查询平台账号信息
     * @param userVO 查询条件
     * @return 符合条件结果
     */
    List<AppUser> findByCondition(AppUserVO userVO);

    /**
     * 根据条件查询用户总条数
     * @param userVO 查询条件
     * @return 总条数
     */
    int countByCondition(AppUserVO userVO);

    /**
     * 修改平台账号信息
     * @param user 更新账号
     * @return 更新条数
     */
    int update(AppUser user);

    /**
     * 根据id查询用户信息
     * @param userId 用户id
     * @return 符合条件未被删除用户信息
     */
    AppUser findById(String userId);

    /**
     * 删除用户信息
     * @param user 用户信息
     * @return 成功条数
     */
    int delete(AppUser user);
}