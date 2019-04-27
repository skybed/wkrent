package com.wkrent.business.bg.question.dao;

import com.wkrent.common.entity.po.BgQuestionCategory;
import com.wkrent.common.entity.vo.BgQuestionCategoryVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgQuestionCategoryDao {

    /**
     * 根据idList删除问题分类信息
     * @param questionCategoryVO 问题分类idList
     * @return 更新条数
     */
    int delete(BgQuestionCategoryVO questionCategoryVO);

    /**
     * 新增问题分类信息
     * @param record 问题分类信息
     * @return 新增条数
     *
     */
    int insert(BgQuestionCategory record);

    /**
     * 根据id查询未删除问题分类举值信息
     * @param questionCategoryId 枚举值信息
     * @return 枚举值信息
     */
    BgQuestionCategory selectByPrimaryKey(String questionCategoryId);

    /**
     * 根据id更新问题分类信息
     * @param record 问题分类信息
     * @return 更新条数
     */
    int updateByPrimaryKey(BgQuestionCategory record);

    /**
     * 条件查询问题分类信息
     * @param questionCategoryVO 查询条件（目前是dataDictType）
     * @return 符合条件信息
     */
    List<BgQuestionCategoryVO> findByCondition(BgQuestionCategoryVO questionCategoryVO);

    /**
     * 条件查询问题分类总条数
     * @param questionCategoryVO 查询条件（目前是dataDictId）
     * @return 符合条件信息
     */
    int countByCondition(BgQuestionCategoryVO questionCategoryVO);

    /**
     * 根据分类名称查询未删除分类信息
     * @param categoryName 问题分类名称
     * @return 符合条件信息
     */
    BgQuestionCategory queryByName(String categoryName);

    /**
     * 查询未删除分类信息
     * @return 符合条件信息
     */
    List<BgQuestionCategory> queryCategoryList();
}