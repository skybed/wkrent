package com.wkrent.business.bg.question.dao;

import com.wkrent.common.entity.po.BgQuestion;
import com.wkrent.common.entity.vo.BgQuestionVO;

import java.util.List;

/**
 * 数据字典类型
 * @author Administrator
 */
public interface BgQuestionDao {

    /**
     * 根据idList删除问题信息
     * @param question 问题idList
     * @return 更新条数
     */
    int delete(BgQuestionVO question);

    /**
     * 新增问题信息
     * @param record 问题信息
     * @return 新增条数
     *
     */
    int insert(BgQuestion record);

    /**
     * 根据id查询未删除问题举值信息
     * @param questionCategoryId 枚举值信息
     * @return 枚举值信息
     */
    BgQuestion selectByPrimaryKey(String questionCategoryId);

    /**
     * 根据id更新问题信息
     * @param record 问题信息
     * @return 更新条数
     */
    int updateByPrimaryKey(BgQuestion record);

    /**
     * 条件查询问题信息
     * @param question 查询条件
     * @return 符合条件信息
     */
    List<BgQuestionVO> findByCondition(BgQuestionVO question);

    /**
     * 条件查询问题总条数
     * @param question 查询条件（目前是dataDictId）
     * @return 符合条件信息
     */
    int countByCondition(BgQuestionVO question);

    /**
     * 根据问题分类Id查询问题信息
     * @param categoryId 查询条件
     * @return 符合条件信息
     */
    List<BgQuestion> findByCatId(String categoryId);

    /**
     * 根据名称查询未删除问题信息
     * @param questionName 问题名称
     * @return 符合条件信息
     */
    List<BgQuestion> queryByName(String questionName);
}