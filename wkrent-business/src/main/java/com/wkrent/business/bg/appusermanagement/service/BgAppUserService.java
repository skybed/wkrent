package com.wkrent.business.bg.appusermanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.AppUserVO;

public interface BgAppUserService {

    /**
     * 分页查询用户信息
     * @param userVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<AppUserVO> findByCondition(AppUserVO userVO);

    /**
     * 根据用户账号修改用户信息
     * @param userVO 待修改用户信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    void update(AppUserVO userVO, String loginAccount);

    /**
     * 根据id删除用户
     * @param userId 用户id
     * @param loginAccount 当前登录账号
     */
    void delete(String userId, String loginAccount);

    /**
     * 根据userId查询前台用户信息
     * @param userId 前台用户id
     * @return 未被删除user
     */
    BaseAjaxVO findByUserId(String userId);
}
