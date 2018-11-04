package com.wkrent.business.bg.roommanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgRoomVO;

/**
 * @author Administrator
 */
public interface BgRoomService {

    /**
     * 分页查询房源信息
     * @param roomVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgRoomVO> findByCondition(BgRoomVO roomVO);

    /**
     * 新增房源信息
     * @param roomVO 房源信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgRoomVO roomVO, String loginAccount);

    /**
     * 根据房源id修改房源信息
     * @param roomVO 待修改房源信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    void update(BgRoomVO roomVO, String loginAccount);

    /**
     * 根据房源id查询房源详情信息
     * @param roomId 房源id
     * @return 符合条件未删除房源信息
     */
    BaseAjaxVO findByRoomId(String roomId);

    /**
     * 根据房源id更新房源状态
     * @param roomVO 房源信息
     * @param loginAccount 登录账号
     * @return 操作结果
     */
    BaseAjaxVO updateRoomStatusById(BgRoomVO roomVO, String loginAccount);

    /**
     * 房源运营管理
     * @param roomVO 房源信息
     * @param loginAccount 登录账号
     * @return 操作结果
     */
    BaseAjaxVO manage(BgRoomVO roomVO, String loginAccount);
}
