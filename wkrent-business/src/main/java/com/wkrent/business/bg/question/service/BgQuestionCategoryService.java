package com.wkrent.business.bg.question.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgQuestionCategoryVO;

import java.util.List;

/**
 * 问题分类
 * @author Administrator
 */
public interface BgQuestionCategoryService {

    /**
     * 新增问题分类
     * @param questionCategoryVO 问题分类信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgQuestionCategoryVO questionCategoryVO, String loginAccount);

    /**
     * 修改问题分类
     * @param questionCategoryVO 问题分类信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO update(BgQuestionCategoryVO questionCategoryVO, String loginAccount);

    /**
     * 分页查询问题分类信息
     * @param questionCategoryVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgQuestionCategoryVO> findByCondition(BgQuestionCategoryVO questionCategoryVO);

    /**
     * 根据id删除订单
     * @param idList 问题分类IdList
     * @param loginAccount 当前登录账号
     * @param result 返回提示
     */
    void delete(List<String> idList, String loginAccount, BaseAjaxVO result);

    /**
     * 根据分类Id查询对应分类信息
     * @param categoryId 问题分类id
     * @return 未被删除user
     */
    BaseAjaxVO findByCategoryId(String categoryId);

    /**
     * 更新问题分类排序
     * @param bgQuestionCategoryVOList 待更新问题分类排序
     * @param loginAccount 登录账号信息
     * @return 操作结果
     */
    BaseAjaxVO updateCategoryIndex(List<BgQuestionCategoryVO> bgQuestionCategoryVOList, String loginAccount);

    /**
     * 查询所有未删除问题分类信息
     * @return 符合条件问题分类信息
     */
    List<BgQuestionCategoryVO> queryCategoryList();
}
