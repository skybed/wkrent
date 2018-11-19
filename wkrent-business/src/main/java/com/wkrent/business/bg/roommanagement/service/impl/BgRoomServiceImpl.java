package com.wkrent.business.bg.roommanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.business.bg.datadict.service.BgDataDictValueService;
import com.wkrent.business.bg.ordermanagement.service.OrderService;
import com.wkrent.business.bg.roommanagement.dao.BgRoomDao;
import com.wkrent.business.bg.roommanagement.service.BgRoomService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.OrderStatusEnum;
import com.wkrent.common.entity.enums.RoomStatusEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgRoom;
import com.wkrent.common.entity.vo.BgDataDictValueVO;
import com.wkrent.common.entity.vo.BgPicAttachVO;
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

    @Autowired
    private BgPicAttachService bgPicAttachService;

    @Autowired
    private OrderService orderService;

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
        if(total <= 0){
            return pageResult;
        }
        List<BgRoomVO> bgRoomVOList = bgRoomDao.findByCondition(roomVO);
        List<String> roomIdList = Lists.newArrayList();
        for(BgRoomVO bgRoomVO : bgRoomVOList){
            roomIdList.add(bgRoomVO.getBgRoomId());
        }
        Map<String, List<String>> fileIdMap = bgPicAttachService.selectFileIdByOwnerIdList(roomIdList);
        if(!fileIdMap.isEmpty()){
            for(BgRoomVO bgRoomVO : bgRoomVOList){
                bgRoomVO.setAttachIdList(fileIdMap.get(bgRoomVO.getBgRoomId()));
            }
        }
        setRoomLabelName(bgRoomVOList);
        pageResult.setRows(bgRoomVOList);
        pageResult.setTotal(total);
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
        //设置附件表房源id信息
        bgPicAttachService.updateAttachOwnerId(roomVO.getAttachIdList(), room.getBgRoomId());
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
        //设置附件表房源id信息
        if(CollectionUtils.isNotEmpty(roomVO.getAttachIdList())){
            bgPicAttachService.updateAttachOwnerId(roomVO.getAttachIdList(), roomVO.getBgRoomId());
        }
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
        //查询附件信息
        List<BgPicAttachVO> attachVOList = bgPicAttachService.selectByOwnerId(roomVO.getBgRoomId());
        List<String> attachIdList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(attachVOList)){
            for (BgPicAttachVO attachVO : attachVOList){
                attachIdList.add(attachVO.getPicAttachId());
            }
        }
        roomVO.setAttachIdList(attachIdList);
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
     * @param roomId 房源Id
     * @return 更新条数
     */
    @Override
    public BaseAjaxVO soldOut(String roomId, String loginAccount) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(roomId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("下架房源失败，房源Id不能为空！");
            return baseAjaxVO;
        }
        BgRoom bgRoom = bgRoomDao.selectByPrimaryKey(roomId);
        if(bgRoom == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("下架房源失败，当前房源不存在或已被删除！");
            return baseAjaxVO;
        }
        BgRoom updateRoom = new BgRoom();
        updateRoom.setBgRoomId(roomId);
        updateRoom.setDescription(RoomStatusEnum.SOLD_OUT.getCode());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRoom, loginAccount);
        bgRoomDao.updateRoomStatusById(updateRoom);
        //更新当前房源下订单状态为房源已下架
        orderService.updateStatusByRoomId(roomId, OrderStatusEnum.SOLD_OUT.getCode(), loginAccount);
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
