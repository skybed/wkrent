package com.wkrent.business.bg.feedbackmanagement.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.AppFeedbackVO;

import java.util.List;

public interface FeedBackService {

    /**
     * 分页查询用户反馈信息
     * @param feedBackVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<AppFeedbackVO> findByCondition(AppFeedbackVO feedBackVO);

    /**
     * 根据id删除用户反馈
     * @param feedBackIdList 用户反馈id
     * @param loginAccount 当前登录账号
     */
    void delete(List<String> feedBackIdList, String loginAccount);

    /**
     * 根据feedBackId查询前台用户反馈信息
     * @param feedBackId 前台用户反馈id
     * @return 未被删除user
     */
    BaseAjaxVO findByFeedId(String feedBackId);

    /**
     * 运营管理
     * @param appFeedbackVO 反馈信息
     * @param loginAccount 操作人账号
     * @return 操作结果
     */
    void manage(AppFeedbackVO appFeedbackVO, String loginAccount);
}
