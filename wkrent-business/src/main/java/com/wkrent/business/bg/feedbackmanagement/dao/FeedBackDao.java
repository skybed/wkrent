package com.wkrent.business.bg.feedbackmanagement.dao;

import com.wkrent.common.entity.po.AppFeedback;
import com.wkrent.common.entity.vo.AppFeedbackVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface FeedBackDao {

    /**
     * 条件查询平台账号信息
     * @param feedBackVO 查询条件
     * @return 符合条件结果
     */
    List<AppFeedbackVO> findByCondition(AppFeedbackVO feedBackVO);

    /**
     * 根据条件查询用户总条数
     * @param feedBackVO 查询条件
     * @return 总条数
     */
    int countByCondition(AppFeedbackVO feedBackVO);

    /**
     * 更新反馈状态
     * @param feedBack 更新账号
     * @return 更新条数
     */
    int updateStatus(AppFeedback feedBack);

    /**
     * 根据id查询用户信息
     * @param userId 用户id
     * @return 符合条件未被删除用户信息
     */
    AppFeedback findById(String userId);

    /**
     * 删除用户信息
     * @param feedBack 用户信息
     * @return 成功条数
     */
    int delete(AppFeedbackVO feedBack);
}