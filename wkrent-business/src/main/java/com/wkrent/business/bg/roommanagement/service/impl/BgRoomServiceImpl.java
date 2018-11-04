package com.wkrent.business.bg.roommanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.datadict.service.BgDataDictValueService;
import com.wkrent.business.bg.roommanagement.dao.BgRoomDao;
import com.wkrent.business.bg.roommanagement.service.BgRoomService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.RoomStatusEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgRoom;
import com.wkrent.common.entity.vo.BgDataDictValueVO;
import com.wkrent.common.entity.vo.BgRoomVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator 
 */
@Service
public class BgRoomServiceImpl implements BgRoomService{

    @Autowired
    private BgRoomDao bgRoomDao;

    @Autowired
    private BgDataDictValueService bgDataDictValueService;

    /**
     * 分页查询房源信息
     *
     * @param roomVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgRoomVO> findByCondition(BgRoomVO roomVO) {
        PageResult<BgRoomVO> pageResult = new PageResult<>();

        int total = bgRoomDao.countByCondition(roomVO);
        if(total > 0){
            List<BgRoomVO> bgMerchantList = bgRoomDao.findByCondition(roomVO);
            setRoomLabelName(bgMerchantList);
            pageResult.setRows(BeanUtil.copyList(bgMerchantList, BgRoomVO.class));
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    private void setRoomLabelName(List<BgRoomVO> roomVOList){
        if(CollectionUtils.isEmpty(roomVOList)){
            return;
        }
        //查询房源标签信息转换为Map ，key：valueId，value：valueName
        List<BgDataDictValueVO> dataDictValueVOList =
                bgDataDictValueService.queryDictValueList(Constants.DICT_TYPE_HOUSE_LABEL);
        Map<String, String> dicValueMap = new HashMap<>();
        for(BgDataDictValueVO valueVO : dataDictValueVOList){
            if(!dicValueMap.containsKey(valueVO.getBgDataDictValueId())){
                dicValueMap.put(valueVO.getBgDataDictValueId(), valueVO.getBgDataDictValue());
            }
        }
        for(BgRoomVO roomVO : roomVOList){
            String roomTips = roomVO.getBgRoomTips();
            if(StringUtils.isBlank(roomTips)){
                continue;
            }
            List<String> tipList = Lists.newArrayList(roomTips.split(", "));
            List<String> tipTextList = Lists.newArrayList();
            for(String tip : tipList){
                if(dicValueMap.containsKey(tip)){
                    tipTextList.add(dicValueMap.get(tip));
                }
            }
            roomVO.setBgRoomTipsText(StringUtils.join(tipTextList, ", "));
        }
    }

    /**
     * 新增房源信息
     *
     * @param roomVO       房源信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO insert(BgRoomVO roomVO, String loginAccount) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        //校验商家全称 & 启用状态是否为空
        checkRoomInfo(roomVO);
        String code = UUIDUtil.getCodeInfo("FY", 4);
        BgRoom room = BeanUtil.copyBean(roomVO, BgRoom.class);
        room.setBgRoomNumber(code);
        room.setBgRoomId(UUIDUtil.getUUID());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, room, loginAccount);
        //设置房源标签，多条用，分割
        room.setBgRoomTips(getRoomLabelInfo(roomVO.getRoomLabelIdList()));
        bgRoomDao.insert(room);
        //TODO 设置附件表房源id信息

        roomVO.setBgRoomNumber(code);
        roomVO.setBgRoomId(room.getBgRoomId());
        baseAjaxVO.setResult(roomVO);
        return baseAjaxVO;
    }

    private String getRoomLabelInfo(List<String> roomLabelList){
        if(CollectionUtils.isEmpty(roomLabelList)){
            return StringUtils.EMPTY;
        }
        return StringUtils.join(roomLabelList, ", ");
    }

    /**
     * 根据房源id修改房源信息
     *
     * @param roomVO       待修改房源信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    @Override
    public void update(BgRoomVO roomVO, String loginAccount) {

        //校验房源必填项是否为空
        checkRoomInfo(roomVO);
        BgRoom updateData = BeanUtil.copyBean(roomVO, BgRoom.class);
        updateData.setBgRoomTips(getRoomLabelInfo(roomVO.getRoomLabelIdList()));
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateData, loginAccount);
        //TODO 若fileIdList不为空，则更新 fileInfo房源id信息
        bgRoomDao.updateByPrimaryKey(updateData);
    }

    /**
     * 根据房源id查询房源详情信息
     *
     * @param roomId 房源id
     * @return 符合条件未删除房源信息
     */
    @Override
    public BaseAjaxVO findByRoomId(String roomId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(roomId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("房源id不能为空！");
            return baseAjaxVO;
        }
        BgRoom room = bgRoomDao.selectByPrimaryKey(roomId);
        if(room == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("房源不存在或者已被删除！");
            return baseAjaxVO;
        }
        BgRoomVO roomVO = BeanUtil.copyBean(room, BgRoomVO.class);
        roomVO.setRoomLabelIdList(getLabelIdList(room.getBgRoomTips()));
        //TODO 查询附件信息
        baseAjaxVO.setResult(roomVO);
        return baseAjaxVO;
    }
    private List<String> getLabelIdList(String roomTips){
        if(StringUtils.isBlank(roomTips)){
            return Lists.newArrayList();
        }
        return Lists.newArrayList(StringUtils.split(", "));
    }

    /**
     * 根据房源id更新房源状态
     * 房源id， 状态 不能为空
     * @param roomVO 房源信息
     * @return 更新条数
     */
    @Override
    public BaseAjaxVO updateRoomStatusById(BgRoomVO roomVO, String loginAccount) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(roomVO.getBgRoomId()) || StringUtils.isBlank(roomVO.getBgRoomStatus())){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("更新房源状态失败，房源Id&状态不能为空！");
            return baseAjaxVO;
        }
        BgRoom bgRoom = bgRoomDao.selectByPrimaryKey(roomVO.getBgRoomId());
        if(bgRoom == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("更新房源状态失败，当前房源不存在或已被删除！");
            return baseAjaxVO;
        }
        if(RoomStatusEnum.getByCode(roomVO.getBgRoomStatus()) == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("更新房源状态失败，房源状态有误！");
            return baseAjaxVO;
        }
        BgRoom updateRoom = new BgRoom();
        updateRoom.setBgRoomId(roomVO.getBgRoomId());
        updateRoom.setBgRoomStatus(roomVO.getBgRoomStatus());
        updateRoom.setDescription(roomVO.getDescription());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRoom, loginAccount);
        bgRoomDao.updateRoomStatusById(updateRoom);
        return baseAjaxVO;
    }

    /**
     * 房源运营管理
     * 房源id， 状态 不能为空
     * @param roomVO 房源信息
     * @return 更新条数
     */
    @Override
    public BaseAjaxVO manage(BgRoomVO roomVO, String loginAccount) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(roomVO.getBgRoomId()) || StringUtils.isBlank(roomVO.getIsStores())){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("更新房源状态失败，房源Id&上下架状态不能为空！");
            return baseAjaxVO;
        }
        BgRoom bgRoom = bgRoomDao.selectByPrimaryKey(roomVO.getBgRoomId());
        if(bgRoom == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("更新房源上下架状态失败，当前房源不存在或已被删除！");
            return baseAjaxVO;
        }
        BgRoom updateRoom = new BgRoom();
        updateRoom.setBgRoomId(roomVO.getBgRoomId());
        updateRoom.setIsStores(roomVO.getIsStores());
        updateRoom.setDescription(roomVO.getDescription());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRoom, loginAccount);
        bgRoomDao.manage(updateRoom);
        return baseAjaxVO;
    }

    /**
     * 校验新增 or 修改房源信息合法性
     * @param roomVO 房源信息
     */
    private void checkRoomInfo(BgRoomVO roomVO){
        if (StringUtils.isBlank(roomVO.getBgRoomName())) {
            throw new WkRentException("保存房源信息失败，房源标题不能为空！");
        }
        if(StringUtils.isBlank(roomVO.getBgRoomAddressCountry())
                && StringUtils.isBlank(roomVO.getBgRoomAddressCity())
                && StringUtils.isBlank(roomVO.getBgRoomAddressDetail())){
            throw new WkRentException("保存房源信息失败，地址不能为空！");
        }
        if(roomVO.getBgRoomAddressX() == null || roomVO.getBgRoomAddressY() == null){
            throw new WkRentException("保存房源信息失败，房源经纬度不能为空！");
        }
        if (roomVO.getBgRoomPrice() == null || StringUtils.isBlank(roomVO.getBgRoomPriceUnit())) {
            throw new WkRentException("保存房源信息失败，房源价格或者价格单位不能为空！");
        }
        if(roomVO.getBgRoomMaxAppointNum() == null){
            throw new WkRentException("保存房源信息失败，房源最大预约数不能为空！");
        }
        if(StringUtils.isBlank(roomVO.getBgRoomRecommend())){
            throw new WkRentException("保存房源信息失败，是否推荐房源不能为空！");
        }
        if(CollectionUtils.isEmpty(roomVO.getRoomLabelIdList())){
            throw new WkRentException("保存房源信息失败，房源标签不能为空！");
        }
        if(StringUtils.isBlank(roomVO.getBgRoomMerchantId())){
            throw new WkRentException("保存房源信息失败，所属房屋公司不能为空！");
        }
        if(StringUtils.isBlank(roomVO.getBgRoomProperty())){
            throw new WkRentException("保存房源信息失败，房屋性质不能为空！");
        }
        if(StringUtils.isBlank(roomVO.getBgRoomType())){
            throw new WkRentException("保存房源信息失败，房屋类型不能为空！");
        }
        if(StringUtils.isBlank(roomVO.getBgRoomDetail())){
            throw new WkRentException("保存房源信息失败，房屋详情不能为空！");
        }
    }
}
