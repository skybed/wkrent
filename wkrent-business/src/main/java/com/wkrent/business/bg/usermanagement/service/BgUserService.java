package com.wkrent.business.bg.usermanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgUser;
import com.wkrent.common.entity.vo.BgUserVO;

import java.util.List;

public interface BgUserService {

    List<BgUser> getAllUser();

    /**
     * 根据用户名查询用户信息
     * @param userAccount 用户名
     * @return 符合条件用户信息
     */
    BgUser findByUserAccount(String userAccount);

    /**
     * 分页查询用户信息
     * @param userVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgUserVO> findByCondition(BgUserVO userVO);

    /**
     * 新增用户信息
     * @param bgUserVO 用户信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgUserVO bgUserVO, String loginAccount);

    /**
     * 根据用户账号修改用户信息
     * @param bgUserVO 待修改用户信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    void update(BgUserVO bgUserVO, String loginAccount);

    /**
     * 根据id删除用户
     * @param userId 用户id
     * @param loginAccount 当前登录账号
     */
    void delete(String userId, String loginAccount);

    /**
     * 根据id锁定用户
     * @param userId 用户id
     * @param loginAccount 当前登录账号
     */
    void lockAccount(String userId, String loginAccount);

    /**
     * 根据id解锁用户
     * @param userId 用户id
     * @param loginAccount 当前登录账号
     */
    void unlockAccount(String userId, String loginAccount);

}
