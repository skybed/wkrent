package com.wkrent.business.bg.question.service.impl;

import com.wkrent.business.bg.question.dao.BgQuestionCategoryDao;
import com.wkrent.business.bg.question.service.BgQuestionCategoryService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgQuestionCategory;
import com.wkrent.common.entity.vo.BgQuestionCategoryVO;
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
public class BgQuestionCategoryServiceImpl implements BgQuestionCategoryService{

    @Autowired
    private BgQuestionCategoryDao bgQuestionCategoryDao;

    private Boolean checkCategoryInfo(BgQuestionCategoryVO questionCategoryVO, Boolean isUpdate){
        if(StringUtils.isBlank(questionCategoryVO.getBgQuestionCatName())){
            throw new WkRentException("保存问题分类信息失败，分类名称不能为空");
        }
        if(isUpdate && StringUtils.isBlank(questionCategoryVO.getBgQuestionCatId())){
            throw new WkRentException("保存问题分类信息失败，分类id不能为空");
        }
        return true;
    }

    /**
     * 设置新增问题分类排序字段
     * @param questionCategory
     */
    private void setCategoryIndex(BgQuestionCategory questionCategory){
        int maxCount = bgQuestionCategoryDao.countByCondition(new BgQuestionCategoryVO());
        questionCategory.setBgQuestionCatIndex(maxCount + 1);
    }

    /**
     * 新增问题分类
     *
     * @param questionCategoryVO   问题分类信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO insert(BgQuestionCategoryVO questionCategoryVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验分类信息
        if(checkCategoryInfo(questionCategoryVO, false)){
            BgQuestionCategory questionCategory =
                    bgQuestionCategoryDao.queryByName(questionCategoryVO.getBgQuestionCatName());
            //校验当前分类是否存在
            if(questionCategory != null){
                throw new WkRentException("新增分类失败，分类名称已存在");
            }
            BgQuestionCategory bgQuestionCategory = new BgQuestionCategory();
            bgQuestionCategory.setBgQuestionCatId(UUIDUtil.getUUID());
            bgQuestionCategory.setBgQuestionCatName(questionCategoryVO.getBgQuestionCatName());
            bgQuestionCategory.setDescription(questionCategoryVO.getDescription());
            setCategoryIndex(bgQuestionCategory);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgQuestionCategory, loginAccount);

            int result = bgQuestionCategoryDao.insert(bgQuestionCategory);
            if(result != 1){
                throw new WkRentException("新增问题分类失败，服务器异常！");
            }
            questionCategoryVO.setBgQuestionCatIndex(bgQuestionCategory.getBgQuestionCatIndex());
            questionCategoryVO.setBgQuestionCatId(bgQuestionCategory.getBgQuestionCatId());
            ajaxVO.setResult(questionCategoryVO);
        }
        return ajaxVO;
    }

    /**
     * 修改问题分类
     *
     * @param questionCategoryVO 问题分类信息
     * @param loginAccount       当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO update(BgQuestionCategoryVO questionCategoryVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验分类信息
        if(checkCategoryInfo(questionCategoryVO, true)){
            BgQuestionCategory questionCategory =
                    bgQuestionCategoryDao.queryByName(questionCategoryVO.getBgQuestionCatName());

            //校验当前分类存在，且根据名称查询分类与当前待修改分类id不同，则不允许修改
            if(questionCategory != null &&
                    !StringUtils.equals(questionCategoryVO.getBgQuestionCatId(), questionCategory.getBgQuestionCatId())){
                throw new WkRentException("修改分类失败，分类名称已存在");
            }
            BgQuestionCategory bgQuestionCategory = new BgQuestionCategory();
            bgQuestionCategory.setBgQuestionCatId(questionCategoryVO.getBgQuestionCatId());
            bgQuestionCategory.setBgQuestionCatName(questionCategoryVO.getBgQuestionCatName());
            bgQuestionCategory.setDescription(questionCategoryVO.getDescription());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, bgQuestionCategory, loginAccount);

            int result = bgQuestionCategoryDao.updateByPrimaryKey(bgQuestionCategory);
            if(result != 1){
                throw new WkRentException("修改问题分类失败，服务器异常！");
            }
            ajaxVO.setResult(questionCategoryVO);
        }
        return ajaxVO;
    }

    /**
     * 分页查询问题分类信息
     *
     * @param questionCategoryVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgQuestionCategoryVO> findByCondition(BgQuestionCategoryVO questionCategoryVO) {
        PageResult<BgQuestionCategoryVO> pageResult = new PageResult<>();

        int total = bgQuestionCategoryDao.countByCondition(questionCategoryVO);
        if(total > 0){
            List<BgQuestionCategoryVO> questionCategoryVOList =
                    bgQuestionCategoryDao.findByCondition(questionCategoryVO);
            pageResult.setRows(questionCategoryVOList);
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    /**
     * 根据id删除订单
     *
     * @param idList       问题分类IdList
     * @param loginAccount 当前登录账号
     * @param result       返回提示
     */
    @Override
    public void delete(List<String> idList, String loginAccount, BaseAjaxVO result) {
        if(CollectionUtils.isEmpty(idList)){
            throw new WkRentException("删除问题分类失败，问题分类Id不能为空！");
        }
        BgQuestionCategoryVO questionCategoryVO = new BgQuestionCategoryVO();
        questionCategoryVO.setBgQuestionCatIdList(idList);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Delete, questionCategoryVO, loginAccount);
        bgQuestionCategoryDao.delete(questionCategoryVO);
    }

    /**
     * 根据分类Id查询对应分类信息
     *
     * @param categoryId 问题分类id
     * @return 未被删除user
     */
    @Override
    public BaseAjaxVO findByCategoryId(String categoryId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(categoryId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("问题分类Id不能为空");
            return baseAjaxVO;
        }
        BgQuestionCategory questionCategory = bgQuestionCategoryDao.selectByPrimaryKey(categoryId);
        if(questionCategory == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("问题分类不存在或已被删除！");
            return baseAjaxVO;
        }
        baseAjaxVO.setResult(BeanUtil.copyBean(questionCategory, BgQuestionCategoryVO.class));
        return baseAjaxVO;
    }

    /**
     * 更新问题分类排序
     *
     * @param bgQuestionCategoryVOList 待更新问题分类排序
     * @return 操作结果
     */
    @Override
    public BaseAjaxVO updateCategoryIndex(List<BgQuestionCategoryVO> bgQuestionCategoryVOList, String loginAccount) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(CollectionUtils.isEmpty(bgQuestionCategoryVOList)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("待更新数据不能为空");
            return baseAjaxVO;
        }
        for(BgQuestionCategoryVO bgQuestionCategoryVO : bgQuestionCategoryVOList){
            if(StringUtils.isBlank(bgQuestionCategoryVO.getBgQuestionCatId())){
                baseAjaxVO.setCode(Constants.FAILED_CODE);
                baseAjaxVO.setText("待更新问题分类Id不能为空");
                return baseAjaxVO;
            }
            if(bgQuestionCategoryVO.getBgQuestionCatIndex() == null
                    || bgQuestionCategoryVO.getBgQuestionCatIndex() <= 0){
                baseAjaxVO.setCode(Constants.FAILED_CODE);
                baseAjaxVO.setText("待更新问题分类, 序号不合法");
                return baseAjaxVO;
            }
        }
        for(BgQuestionCategoryVO bgQuestionCategoryVO : bgQuestionCategoryVOList){
            BgQuestionCategory bgQuestionCategory = new BgQuestionCategory();
            bgQuestionCategory.setBgQuestionCatId(bgQuestionCategoryVO.getBgQuestionCatId());
            bgQuestionCategory.setBgQuestionCatIndex(bgQuestionCategoryVO.getBgQuestionCatIndex());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, bgQuestionCategory, loginAccount);
            bgQuestionCategoryDao.updateIndexByPrimaryKey(bgQuestionCategory);
        }
        return baseAjaxVO;
    }

    /**
     * 查询所有未删除问题分类信息
     *
     * @return 符合条件问题分类信息
     */
    @Override
    public List<BgQuestionCategoryVO> queryCategoryList() {
        List<BgQuestionCategory> bgQuestionCategoryList = bgQuestionCategoryDao.queryCategoryList();
        return BeanUtil.copyList(bgQuestionCategoryList, BgQuestionCategoryVO.class);
    }
}
