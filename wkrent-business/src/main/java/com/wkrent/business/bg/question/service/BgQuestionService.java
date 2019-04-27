package com.wkrent.business.bg.question.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgQuestionVO;

import java.util.List;

/**
 * 数据字典
 * @author Administrator
 */
public interface BgQuestionService {

    /**
     * 分页查询问题信息
     * @param questionVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgQuestionVO> findByCondition(BgQuestionVO questionVO);

    /**
     * 根据问题分类查询问题List
     * @param questionCategoryId 查询条件
     * @return 符合条件枚举值信息
     */
    List<BgQuestionVO> queryByCategoryId(String questionCategoryId);

    /**
     * 根据问题id查询问题
     * @param questionId 查询条件
     * @return 符合条件信息
     */
    BaseAjaxVO findById(String questionId);

    /**
     * 新增问题信息
     * @param questionVO 问题信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgQuestionVO questionVO, String loginAccount);

    /**
     * 修改问题信息
     * @param questionVO 问题
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    BaseAjaxVO update(BgQuestionVO questionVO, String loginAccount);

    /**
     * 根据idList删除问题信息
     * @param questionIdList 问题idList
     * @param loginAccount 当前登录账号
     * @param result 操作结果
     */
    void delete(List<String> questionIdList, String loginAccount, BaseAjaxVO result);

}
