package com.wkrent.business.bg.area.service.impl;

import com.wkrent.business.bg.area.dao.BgAreaDao;
import com.wkrent.business.bg.area.service.BgAreaService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.po.BgArea;
import com.wkrent.common.entity.vo.BgAreaVO;
import com.wkrent.common.util.BeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国家地区
 * @author Administrator
 */
@Service
public class BgAreaServiceImpl implements BgAreaService{

    @Autowired
    private BgAreaDao bgAreaDao;

    /**
     * 查询国家信息
     * @return 符合条件国家信息
     */
    @Override
    public BaseAjaxVO queryCountryInfo() {
        List<BgArea> areaList = bgAreaDao.queryAreaByLevel(1);
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(CollectionUtils.isNotEmpty(areaList)){
            baseAjaxVO.setResult(BeanUtil.copyList(areaList, BgAreaVO.class));
        }
        return baseAjaxVO;
    }

    /**
     * 根据上级code查询地区信息
     * @param parentCode 上级code
     * @return 符合条件地区信息
     */
    @Override
    public BaseAjaxVO findByParentCode(String parentCode) {

        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(parentCode)){
            return baseAjaxVO;
        }
        List<BgArea> areaList = bgAreaDao.queryAreaByParentCode(parentCode);
        if(CollectionUtils.isNotEmpty(areaList)){
            baseAjaxVO.setResult(BeanUtil.copyList(areaList, BgAreaVO.class));
        }
        return baseAjaxVO;
    }
}
