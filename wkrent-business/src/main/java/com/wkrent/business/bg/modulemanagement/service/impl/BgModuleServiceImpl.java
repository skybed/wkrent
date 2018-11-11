package com.wkrent.business.bg.modulemanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.business.bg.modulemanagement.dao.BgModuleDao;
import com.wkrent.business.bg.modulemanagement.service.BgModuleService;
import com.wkrent.common.entity.enums.UploadFileTypeEnum;
import com.wkrent.common.entity.po.BgModule;
import com.wkrent.common.entity.vo.BgModuleVO;
import com.wkrent.common.entity.vo.BgPicAttachVO;
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
 * @author Administrator
 */
@Service
public class BgModuleServiceImpl implements BgModuleService {

    @Autowired
    private BgModuleDao bgModuleDao;

    @Autowired
    private BgPicAttachService bgPicAttachService;


    /**
     * 保存模块设置
     *
     * @param moduleVO 模块设置信息
     */
    @Override
    public void save(BgModuleVO moduleVO, String loginAccount) {

        if(StringUtils.isBlank(moduleVO.getBgModuleType())){
            throw new WkRentException("模块类型不能为空");
        }
        BgModule module = bgModuleDao.findModuleByType(moduleVO.getBgModuleType());
        BgModule saveData = BeanUtil.copyBean(moduleVO, BgModule.class);
        if(module == null){
            //如果未查询到对应的模块信息，则新增
            saveData.setBgModuleId(UUIDUtil.getUUID());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, saveData, loginAccount);
            bgModuleDao.insert(saveData);
        }else {
            //若查询到对应的模块信息，则更新
            saveData.setBgModuleId(module.getBgModuleId());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, saveData, loginAccount);
        }
        //更新附件归属id信息
        bgPicAttachService.updateAttachOwnerId(
                Lists.newArrayList(moduleVO.getCustomerServiceFileId(), moduleVO.getPlatformFileId()),
                saveData.getBgModuleId());

    }

    /**
     * 根据类型查询模块设置信息
     *
     * @param moduleType 模块类型
     * @return 符合条件模块设置
     */
    @Override
    public BgModuleVO findByType(String moduleType) {
        if(StringUtils.isBlank(moduleType)){
            throw new WkRentException("模块类型不能为空");
        }
        BgModule bgModule = bgModuleDao.findModuleByType(moduleType);
        if(bgModule == null){
            return null;
        }
        BgModuleVO moduleVO = BeanUtil.copyBean(bgModule, BgModuleVO.class);
        List<BgPicAttachVO> attachVOList = bgPicAttachService.selectByOwnerId(moduleVO.getBgModuleId());
        if(CollectionUtils.isNotEmpty(attachVOList)){
            for(BgPicAttachVO attachVO : attachVOList){
                if(UploadFileTypeEnum.CUSTOMER_FILE.getCode().equals(attachVO.getPicAttachType())){
                    moduleVO.setCustomerServiceFileId(attachVO.getPicAttachId());
                }
                if(UploadFileTypeEnum.PLATFORM_FILE.getCode().equals(attachVO.getPicAttachType())){
                    moduleVO.setPlatformFileId(attachVO.getPicAttachId());
                }
            }
        }
        return moduleVO;
    }
}
