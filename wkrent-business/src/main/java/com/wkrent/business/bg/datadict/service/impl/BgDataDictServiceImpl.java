package com.wkrent.business.bg.datadict.service.impl;

import com.wkrent.business.bg.datadict.dao.BgDataDictDao;
import com.wkrent.business.bg.datadict.service.BgDataDictService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.po.BgDataDict;
import com.wkrent.common.entity.po.BgDataDictValue;
import com.wkrent.common.entity.vo.BgDataDictVO;
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
 * 数据字典类型
 * @author Administrator
 */
@Service
public class BgDataDictServiceImpl implements BgDataDictService{

    @Autowired
    private BgDataDictDao bgDataDictDao;
    /**
     * 新增枚举类型信息
     *
     * @param dataDictVO   数据字典类型
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO insert(BgDataDictVO dataDictVO, String loginAccount) {

        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验用户信息
        if(checkDictInfo(dataDictVO)){
            //校验当前枚举是否存在
            BgDataDict dataDict = bgDataDictDao.findByType(dataDictVO.getBgDataDictType());
            if(dataDict != null){
                throw new WkRentException("新增分类失败，分类名称已存在");
            }
            BgDataDict bgDataDict = new BgDataDict();
            bgDataDict.setBgDataDictId(UUIDUtil.getUUID());
            bgDataDict.setBgDataDictType(dataDictVO.getBgDataDictType());
            bgDataDict.setDescription(dataDictVO.getDescription());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgDataDict, loginAccount);
            int result = bgDataDictDao.insert(bgDataDict);
            if(result != 1){
                throw new WkRentException("新增数据字典值失败，服务器异常！");
            }
            dataDictVO.setBgDataDictId(bgDataDict.getBgDataDictId());
            ajaxVO.setResult(dataDictVO);
        }
        return ajaxVO;
    }

    private Boolean checkDictInfo(BgDataDictVO dataDictVO){
        if(StringUtils.isBlank(dataDictVO.getBgDataDictType())){
            throw new WkRentException("保存数据字典信息失败，分类名称不能为空");
        }
        return true;
    }
}
