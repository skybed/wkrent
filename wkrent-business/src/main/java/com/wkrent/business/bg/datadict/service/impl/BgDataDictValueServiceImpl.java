package com.wkrent.business.bg.datadict.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.datadict.dao.BgDataDictDao;
import com.wkrent.business.bg.datadict.dao.BgDataDictValueDao;
import com.wkrent.business.bg.datadict.service.BgDataDictValueService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgDataDict;
import com.wkrent.common.entity.po.BgDataDictValue;
import com.wkrent.common.entity.vo.BgDataDictValueVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典
 * @author Administrator
 */
@Service
public class BgDataDictValueServiceImpl implements BgDataDictValueService{

    @Autowired
    private BgDataDictValueDao bgDataDictValueDao;

    @Autowired
    private BgDataDictDao bgDataDictDao;

    /**
     * 分页查询枚举信息
     *
     * @param dataDictValueVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgDataDictValueVO> findByCondition(BgDataDictValueVO dataDictValueVO) {

        PageResult<BgDataDictValueVO> pageResult = new PageResult<>();

        int total = bgDataDictValueDao.countByCondition(dataDictValueVO);
        if(total > 0){
            List<BgDataDictValueVO> dataDictValueVOList = bgDataDictValueDao.findByCondition(dataDictValueVO);
            pageResult.setRows(BeanUtil.copyList(dataDictValueVOList, BgDataDictValueVO.class));
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    /**
     * 查询枚举值List
     *
     * @param dictType 查询条件
     * @return 符合条件枚举值信息
     */
    @Override
    public List<BgDataDictValueVO> queryDictValueList(String dictType) {
        if(StringUtils.isBlank(dictType)){
            return Lists.newArrayList();
        }
        return bgDataDictValueDao.queryDictValueByType(dictType);
    }

    /**
     * 新增枚举信息
     *
     * @param dataDictValueVO 数据字典
     * @param loginAccount    当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO insert(BgDataDictValueVO dataDictValueVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验用户信息
        if(checkDictValueInfo(dataDictValueVO)){
            //校验当前枚举是否存在
            BgDataDict dataDict = bgDataDictDao.findByType(dataDictValueVO.getBgDataDictType());
            if(dataDict == null){
                throw new WkRentException("新增数据字典值失败，数据字典已被删除！");
            }
            BgDataDictValueVO condition = new BgDataDictValueVO();
            condition.setBgDataDictType(dataDictValueVO.getBgDataDictType());
            condition.setBgDataDictValue(dataDictValueVO.getBgDataDictValue());
            List<BgDataDictValueVO> dataDictValueVOList = bgDataDictValueDao.findByCondition(condition);
            if(CollectionUtils.isNotEmpty(dataDictValueVOList)){
                throw new WkRentException("新增数据字典值失败，当前数据字典值已存在！");
            }
            BgDataDictValue dictValue = BeanUtil.copyBean(dataDictValueVO, BgDataDictValue.class);
            dictValue.setBgDataDictValueId(UUIDUtil.getUUID());
            dictValue.setBgDataDictId(dataDict.getBgDataDictId());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, dictValue, loginAccount);
            int result = bgDataDictValueDao.insert(dictValue);
            if(result != 1){
                throw new WkRentException("新增数据字典值失败，服务器异常！");
            }
            dataDictValueVO.setBgDataDictValueId(dictValue.getBgDataDictValueId());
            ajaxVO.setResult(dataDictValueVO);
        }
        return ajaxVO;
    }

    private Boolean checkDictValueInfo(BgDataDictValueVO dataDictValueVO){
        if(StringUtils.isBlank(dataDictValueVO.getBgDataDictType())){
            throw new WkRentException("保存数据字典信息失败，类型不能为空");
        }
        if(StringUtils.isBlank(dataDictValueVO.getBgDataDictValue())){
            throw new WkRentException("保存数据字典信息失败，枚举值不能为空");
        }
        return true;
    }

    /**
     * 修改数据字典值
     *
     * @param dataDictValueVO 待数据字典信息
     * @param loginAccount    当前登录账号
     * @return 修改条数
     */
    @Override
    public void update(BgDataDictValueVO dataDictValueVO, String loginAccount) {
        //校验用户信息
        if(checkDictValueInfo(dataDictValueVO)){
            //校验当前枚举是否存在
            BgDataDict dataDict = bgDataDictDao.findByType(dataDictValueVO.getBgDataDictType());
            if(dataDict == null){
                throw new WkRentException("新增数据字典值失败，数据字典已被删除！");
            }
            //根据枚举值&枚举类型查询 数据字典信息
            BgDataDictValueVO condition = new BgDataDictValueVO();
            condition.setBgDataDictType(dataDictValueVO.getBgDataDictType());
            condition.setBgDataDictValue(dataDictValueVO.getBgDataDictValue());
            List<BgDataDictValueVO> dataDictValueVOList = bgDataDictValueDao.findByCondition(condition);
            if(CollectionUtils.isNotEmpty(dataDictValueVOList)){
                for(BgDataDictValueVO dictValueVO : dataDictValueVOList){
                    //若枚举值相同 & id不同，则修改后枚举值已存在
                    if(dictValueVO.getBgDataDictValue().equals(dataDictValueVO.getBgDataDictValue())
                            && !dictValueVO.getBgDataDictValueId().equals(dataDictValueVO.getBgDataDictValueId())){
                        throw new WkRentException("修改数据字典值失败," +
                                dictValueVO.getBgDataDictType() + "-" + dictValueVO.getBgDataDictValue() + "已存在！");
                    }
                }
            }
            BgDataDictValue dictValue = BeanUtil.copyBean(dataDictValueVO, BgDataDictValue.class);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, dictValue, loginAccount);
            int result = bgDataDictValueDao.updateByPrimaryKey(dictValue);
            if(result != 1){
                throw new WkRentException("修改数据字典值失败，服务器异常！");
            }
            dataDictValueVO.setBgDataDictValueId(dictValue.getBgDataDictValueId());
        }
    }

    /**
     * 根据idList删除数据字典信息
     *
     * @param dictValueIdList 枚举值idList
     * @param loginAccount    当前登录账号
     */
    @Override
    public void delete(List<String> dictValueIdList, String loginAccount) {
        if(CollectionUtils.isEmpty(dictValueIdList)){
            return;
        }
        BgDataDictValueVO bgDataDictValueVO = new BgDataDictValueVO();
        bgDataDictValueVO.setDataDictValueIdList(dictValueIdList);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Delete, bgDataDictValueVO, loginAccount);
        bgDataDictValueDao.delete(bgDataDictValueVO);
    }

    /**
     * 根据idList禁用数据字典值
     *
     * @param dictValueIdList 数据字典值idList
     * @param loginAccount    当前登录账号
     */
    @Override
    public void disable(List<String> dictValueIdList, String loginAccount) {

    }

    /**
     * 根据idList启用数据字典值
     *
     * @param dictValueIdList 数据字典值idList
     * @param loginAccount    当前登录账号
     */
    @Override
    public void enable(List<String> dictValueIdList, String loginAccount) {

    }
}
