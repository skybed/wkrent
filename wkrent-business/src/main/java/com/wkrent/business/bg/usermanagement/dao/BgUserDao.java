package com.wkrent.business.bg.usermanagement.dao;

import com.wkrent.common.entity.po.BgUser;
import com.wkrent.common.entity.vo.BgUserVO;

import java.util.List;

public interface BgUserDao {

    List<BgUser> getAllUserList();

    /**
     * 根据用户名查询用户信息
     * @param userAccount 用户名
     * @return 符合条件用户信息
     */
    BgUser findByUserAccount(String userAccount);

    /**
     * 条件查询平台账号信息
     * @param bgUserVO 查询条件
     * @return 符合条件结果
     */
    List<BgUser> findByCondition(BgUserVO bgUserVO);

    /**
     * 根据条件查询用户总条数
     * @param bgUserVO 查询条件
     * @return 总条数
     */
    int countByCondition(BgUserVO bgUserVO);

    /**
     * 新增平台账号信息
     * @param bgUser 新增账号
     * @return 新增条数
     */
    int insertUser(BgUser bgUser);

    /**
     * 修改平台账号信息
     * @param bgUser 更新账号
     * @return 更新条数
     */
    int update(BgUser bgUser);

    /**
     * 根据id查询用户信息
     * @param userId 用户id
     * @return 符合条件未被删除用户信息
     */
    BgUser findById(String userId);

    /**
     * 更新用户 锁定状态
     * @param bgUser 用户信息
     * @return 成功条数
     */
    int updateUserStatus(BgUser bgUser);

    /**
     * 删除用户信息
     * @param bgUser 用户信息
     * @return 成功条数
     */
    int delete(BgUser bgUser);
}
