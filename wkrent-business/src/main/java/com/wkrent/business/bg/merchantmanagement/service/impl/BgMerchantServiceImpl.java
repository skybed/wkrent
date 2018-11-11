package com.wkrent.business.bg.merchantmanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.business.bg.merchantmanagement.dao.BgMerchantDao;
import com.wkrent.business.bg.merchantmanagement.service.BgMerchantService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgMerchant;
import com.wkrent.common.entity.po.BgRole;
import com.wkrent.common.entity.vo.BgMerchantVO;
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
public class BgMerchantServiceImpl implements BgMerchantService{

    @Autowired
    private BgMerchantDao bgMerchantDao;

    @Autowired
    private BgPicAttachService bgPicAttachService;

    /**
     * 分页查询商家信息
     *
     * @param merchantVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgMerchantVO> findByCondition(BgMerchantVO merchantVO) {
        PageResult<BgMerchantVO> pageResult = new PageResult<>();

        int total = bgMerchantDao.countByCondition(merchantVO);
        if(total > 0){
            List<BgMerchant> bgMerchantList = bgMerchantDao.findByCondition(merchantVO);
            pageResult.setRows(BeanUtil.copyList(bgMerchantList, BgMerchantVO.class));
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    /**
     * 新增商家信息
     *
     * @param merchantVO   商家信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO insert(BgMerchantVO merchantVO, String loginAccount) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        //校验商家全称 & 启用状态是否为空
        checkMerchantInfo(merchantVO);
        //校验商家全称是否已存在
        BgMerchant merchant = bgMerchantDao.findByName(merchantVO.getBgMerchantName());
        if(merchant != null){
            throw new WkRentException("新增商家信息失败，商家名称已存在！");
        }
        //获取商家Code
        String code = UUIDUtil.getCodeInfo(StringUtils.EMPTY, 4);
        BgMerchant bgMerchant = BeanUtil.copyBean(merchantVO, BgMerchant.class);
        bgMerchant.setBgMerchantNumber(code);
        bgMerchant.setBgMerchantId(UUIDUtil.getUUID());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgMerchant,loginAccount);
        bgMerchantDao.insert(bgMerchant);
        //设置附件表商家id信息
        bgPicAttachService.updateAttachOwnerId(merchantVO.getFileIdList(), bgMerchant.getBgMerchantId());
        merchantVO.setBgMerchantNumber(code);
        merchantVO.setBgMerchantId(bgMerchant.getBgMerchantId());
        baseAjaxVO.setResult(merchantVO);
        return baseAjaxVO;
    }

    /**
     * 根据商家id修改商家信息
     *
     * @param merchantVO   待修改商家信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    @Override
    public void update(BgMerchantVO merchantVO, String loginAccount) {

        //校验商家全称 & 启用状态是否为空
        checkMerchantInfo(merchantVO);
        //校验修改后商家全称 是否跟当前
        BgMerchant merchant = bgMerchantDao.findByName(merchantVO.getBgMerchantName());
        if(merchant != null && !merchant.getBgMerchantId().equals(merchantVO.getBgMerchantId())){
            throw new WkRentException("修改商家信息失败，商家名称已存在！");
        }
        BgMerchant updateData = BeanUtil.copyBean(merchantVO, BgMerchant.class);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateData, loginAccount);
        //若fileIdList不为空，则更新 fileInfo商家id信息
        bgPicAttachService.updateAttachOwnerId(merchantVO.getFileIdList(), merchantVO.getBgMerchantId());
        bgMerchantDao.update(updateData);
    }

    /**
     * 根据商家id查询商家详情信息
     *
     * @param merchantId 商家id
     * @return 符合条件未删除商家信息
     */
    @Override
    public BaseAjaxVO findByMerchantId(String merchantId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(merchantId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("商家id不能为空！");
            return baseAjaxVO;
        }
        BgMerchant merchant = bgMerchantDao.selectByPrimaryKey(merchantId);
        if(merchant == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("商家不存在或者已被删除！");
            return baseAjaxVO;
        }
        baseAjaxVO.setResult(BeanUtil.copyBean(merchant, BgMerchantVO.class));
        return baseAjaxVO;
    }

    private void checkMerchantInfo(BgMerchantVO merchantVO){
        if(merchantVO == null){
            throw new WkRentException("保存商家信息失败，传入商家信息不能为空！");
        }
        if(StringUtils.isBlank(merchantVO.getBgMerchantName())){
            throw new WkRentException("保存商家信息失败，商家全称不能为空！");
        }
        if(StringUtils.isBlank(merchantVO.getIsActive())){
            throw new WkRentException("保存商家信息失败，商家状态不能为空！");
        }
    }

    /**
     * 根据id删除商家
     *
     * @param merchantId   商家id
     * @param loginAccount 当前登录账号
     */
    @Override
    public void delete(String merchantId, String loginAccount) {
        if(StringUtils.isBlank(merchantId)){
            throw new WkRentException("删除商家失败，商家id不能为空！");
        }
        BgMerchant merchant = new BgMerchant();
        merchant.setBgMerchantId(merchantId);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Delete, merchant, loginAccount);
        bgMerchantDao.deleteByPrimaryKey(merchant);
    }

    /**
     * 根据启用商家
     *
     * @param merchantId   商家id
     * @param loginAccount 当前登录账号
     */
    @Override
    public void enable(String merchantId, String loginAccount) {
        if(StringUtils.isBlank(merchantId)){
            throw new WkRentException("启用商家失败，请传入商家id");
        }
        BgMerchant merchant = bgMerchantDao.selectByPrimaryKey(merchantId);
        if(merchant == null){
            throw new WkRentException("启用商家失败，商家信息已被删除");
        }
        //商家已被启用，不能再次启用
        if (Constants.STR_TRUE.equals(merchant.getIsActive())) {
            throw new WkRentException("启用商家失败，商家信息已被启用");
        }
        BgMerchant updateMerchant = new BgMerchant();
        updateMerchant.setBgMerchantId(merchantId);
        updateMerchant.setIsActive(Constants.STR_TRUE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateMerchant, loginAccount);
        int result = bgMerchantDao.updateStatus(updateMerchant);
        if(result != 1){
            throw new WkRentException("启用商家失败，商家信息已变更！");
        }
    }

    /**
     * 根据id停用商家
     *
     * @param merchantId   商家id
     * @param loginAccount 当前登录账号
     */
    @Override
    public void disable(String merchantId, String loginAccount) {
        if(StringUtils.isBlank(merchantId)){
            throw new WkRentException("禁用商家失败，请传入商家id");
        }
        BgMerchant merchant = bgMerchantDao.selectByPrimaryKey(merchantId);
        if(merchant == null){
            throw new WkRentException("禁用商家失败，商家信息已被删除");
        }
        //商家已被禁用，不能再次禁用
        if (Constants.STR_FALSE.equals(merchant.getIsActive())) {
            throw new WkRentException("禁用商家失败，商家信息已被禁用");
        }
        BgMerchant updateMerchant = new BgMerchant();
        updateMerchant.setBgMerchantId(merchantId);
        updateMerchant.setIsActive(Constants.STR_FALSE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateMerchant, loginAccount);
        int result = bgMerchantDao.updateStatus(updateMerchant);
        if(result != 1){
            throw new WkRentException("禁用商家失败，商家信息已变更！");
        }
    }

    /**
     * 查询商家信息
     *
     * @return 符合条件信息
     */
    @Override
    public List<BgMerchantVO> queryAllMerchant() {

        List<BgMerchant> merchantList = bgMerchantDao.queryAllMerchant();
        if(CollectionUtils.isNotEmpty(merchantList)){
            return BeanUtil.copyList(merchantList, BgMerchantVO.class);
        }
        return Lists.newArrayList();
    }
}
