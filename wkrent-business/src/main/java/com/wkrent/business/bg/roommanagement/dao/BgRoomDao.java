package com.wkrent.business.bg.roommanagement.dao;

import com.wkrent.common.entity.po.BgRoom;
import com.wkrent.common.entity.vo.BgRoomVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgRoomDao {


    /**
     * 条件查询房源信息
     * @param bgRoomVO 查询条件
     * @return 符合条件结果
     */
    List<BgRoomVO> findByCondition(BgRoomVO bgRoomVO);

    /**
     * 根据条件查询房源总条数
     * @param bgRoomVO 查询条件
     * @return 总条数
     */
    int countByCondition(BgRoomVO bgRoomVO);
    
    /**
     * 删除房源信息
     * @param room 待删除房源信息
     * @mbg.generated 2018-10-21
     * @return 更新条数
     */
    int delete(BgRoom room);

    /**
     * 新增房源信息
     * @param record 房源信息
     * @mbg.generated 2018-10-21
     * @return 新增条数
     */
    int insert(BgRoom record);

    /**
     * 根据id查询房源信息
     * @mbg.generated 2018-10-21
     * @param bgRoomId 房源id
     * @return 符合条件未删除房源信息
     */
    BgRoom selectByPrimaryKey(String bgRoomId);

    /**
     * 根据id更新房源信息
     * @mbg.generated 2018-10-21
     * @param record 待更新房源信息
     * @return 更新条数
     */
    int updateByPrimaryKey(BgRoom record);

    /**
     * 根据房源id更新房源状态
     * @param room 房源信息
     * @return 更新条数
     */
    int updateRoomStatusById(BgRoom room);

    /**
     * 房源运营管理
     * @param room 房源信息
     * @return 更新条数
     */
    int manage(BgRoom room);
}