package com.wkrent.business.bg.question.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.question.dao.BgQuestionDao;
import com.wkrent.business.bg.question.service.BgQuestionService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgQuestion;
import com.wkrent.common.entity.vo.BgQuestionCategoryVO;
import com.wkrent.common.entity.vo.BgQuestionVO;
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
public class BgQuestionServiceImpl implements BgQuestionService{

    @Autowired
    private BgQuestionDao bgQuestionDao;

    /**
     * 分页查询问题信息
     *
     * @param questionVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgQuestionVO> findByCondition(BgQuestionVO questionVO) {
        PageResult<BgQuestionVO> pageResult = new PageResult<>();

        int total = bgQuestionDao.countByCondition(questionVO);
        if(total > 0){
            List<BgQuestionVO> questionCategoryVOList = bgQuestionDao.findByCondition(questionVO);
            pageResult.setRows(questionCategoryVOList);
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    /**
     * 根据问题分类查询问题List
     *
     * @param questionCategoryId 查询条件
     * @return 符合条件枚举值信息
     */
    @Override
    public List<BgQuestionVO> queryByCategoryId(String questionCategoryId) {
        if(StringUtils.isBlank(questionCategoryId)){
            throw new WkRentException("查询问题失败，问题分类Id不能为空！");
        }
        List<BgQuestion> bgQuestionList = bgQuestionDao.findByCatId(questionCategoryId);
        if(CollectionUtils.isEmpty(bgQuestionList)){
            return Lists.newArrayList();
        }
        return BeanUtil.copyList(bgQuestionList, BgQuestionVO.class, true);
    }

    /**
     * 根据问题id查询问题
     *
     * @param questionId 查询条件
     * @return 符合条件信息
     */
    @Override
    public BaseAjaxVO findById(String questionId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(questionId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("问题Id不能为空");
            return baseAjaxVO;
        }
        BgQuestion bgQuestion = bgQuestionDao.selectByPrimaryKey(questionId);
        if(bgQuestion == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("问题不存在或已被删除！");
            return baseAjaxVO;
        }
        baseAjaxVO.setResult(BeanUtil.copyBean(bgQuestion, BgQuestionVO.class));
        return baseAjaxVO;
    }

    /**
     * 新增问题信息
     *
     * @param questionVO   数据字典
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    @Override
    public BaseAjaxVO insert(BgQuestionVO questionVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验分类信息
        if(checkQuestionInfo(questionVO, false)){
            List<BgQuestion> questionList = bgQuestionDao.queryByName(questionVO.getBgQuestion());
            //校验当前分类下是否存在对应的问题信息
            if(CollectionUtils.isNotEmpty(questionList)){
                for(BgQuestion question : questionList){
                    if(StringUtils.equals(question.getBgQuestionCatId(), questionVO.getBgQuestionCatId())){
                        throw new WkRentException("新增问题失败，该问题分类下问题名称已存在");
                    }
                }
            }

            BgQuestion bgQuestion = new BgQuestion();
            bgQuestion.setBgQuestionId(UUIDUtil.getUUID());
            bgQuestion.setBgQuestionAnswer(questionVO.getBgQuestionAnswer());
            bgQuestion.setBgQuestion(questionVO.getBgQuestion());
            bgQuestion.setBgQuestionCatId(questionVO.getBgQuestionCatId());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgQuestion, loginAccount);

            int result = bgQuestionDao.insert(bgQuestion);
            if(result != 1){
                throw new WkRentException("新增问题失败，服务器异常！");
            }
            questionVO.setBgQuestionId(bgQuestion.getBgQuestionId());
            ajaxVO.setResult(questionVO);
        }
        return ajaxVO;
    }

    /**
     * 修改问题信息
     *
     * @param questionVO   问题
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    @Override
    public BaseAjaxVO update(BgQuestionVO questionVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验分类信息
        if(checkQuestionInfo(questionVO, true)){
            List<BgQuestion> questionList = bgQuestionDao.queryByName(questionVO.getBgQuestion());
            //校验当前分类下是否存在对应的问题信息
            if(CollectionUtils.isNotEmpty(questionList)){
                for(BgQuestion question : questionList){
                    //根据名称查询问题与当前待修改问题id不同，且两问题所属分类id相同, 则不允许修改
                    if(!StringUtils.equals(questionVO.getBgQuestionId(), question.getBgQuestionId()) &&
                            StringUtils.equals(questionVO.getBgQuestionCatId(), question.getBgQuestionCatId())){
                        throw new WkRentException("修改问题失败，该分类下问题名称已存在");
                    }
                }
            }
            BgQuestion bgQuestion = new BgQuestion();
            bgQuestion.setBgQuestionCatId(questionVO.getBgQuestionCatId());
            bgQuestion.setBgQuestion(questionVO.getBgQuestion());
            bgQuestion.setBgQuestionId(questionVO.getBgQuestionId());
            bgQuestion.setBgQuestionAnswer(questionVO.getBgQuestionAnswer());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, bgQuestion, loginAccount);

            int result = bgQuestionDao.updateByPrimaryKey(bgQuestion);
            if(result != 1){
                throw new WkRentException("修改问题失败，服务器异常！");
            }
            ajaxVO.setResult(questionVO);
        }
        return ajaxVO;
    }

    /**
     * 根据idList删除问题信息
     *
     * @param questionIdList 问题idList
     * @param loginAccount   当前登录账号
     * @param result 操作结果
     */
    @Override
    public void delete(List<String> questionIdList, String loginAccount, BaseAjaxVO result) {
        if(CollectionUtils.isEmpty(questionIdList)){
            throw new WkRentException("删除问题失败，问题Id不能为空！");
        }
        BgQuestionVO questionVO = new BgQuestionVO();
        questionVO.setBgQuestionIdList(questionIdList);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Delete, questionVO, loginAccount);
        bgQuestionDao.delete(questionVO);
    }

    private Boolean checkQuestionInfo(BgQuestionVO questionVO, Boolean isUpdate){
        if(StringUtils.isBlank(questionVO.getBgQuestion())){
            throw new WkRentException("保存问题信息失败，问题名称不能为空");
        }
        if(StringUtils.isBlank(questionVO.getBgQuestionCatId())){
            throw new WkRentException("保存问题信息失败，问题分类id不能为空");
        }
        if(StringUtils.isBlank(questionVO.getBgQuestionAnswer())){
            throw new WkRentException("保存问题信息失败，问题答案不能为空");
        }
        if(isUpdate && StringUtils.isBlank(questionVO.getBgQuestionId())){
            throw new WkRentException("保存问题信息失败，问题id不能为空");
        }
        return true;
    }
}
