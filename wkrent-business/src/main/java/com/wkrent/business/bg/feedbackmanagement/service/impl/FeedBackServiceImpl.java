package com.wkrent.business.bg.feedbackmanagement.service.impl;

import com.wkrent.business.bg.feedbackmanagement.dao.FeedBackDao;
import com.wkrent.business.bg.feedbackmanagement.service.FeedBackService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.AppFeedBackStatusEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.AppFeedback;
import com.wkrent.common.entity.vo.AppFeedbackVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackDao feedBackDao;

    /**
     * 分页查询用户信息
     *
     * @param feedBackVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<AppFeedbackVO> findByCondition(AppFeedbackVO feedBackVO) {
        PageResult<AppFeedbackVO> pageResult = new PageResult<>();

        int total = feedBackDao.countByCondition(feedBackVO);
        if(total > 0){
            List<AppFeedbackVO> feeBackList = feedBackDao.findByCondition(feedBackVO);
            pageResult.setRows(feeBackList);
            pageResult.setTotal(total);
        }
        return pageResult;
    }


    /**
     * 根据id删除用户
     *
     * @param feedBackIdList 用户反馈idList
     */
    @Override
    public void delete(List<String> feedBackIdList, String loginAccount) {
        if(CollectionUtils.isEmpty(feedBackIdList)){
            throw new WkRentException("删除用户失败，请传入用户id");
        }
        AppFeedbackVO updateFeedBack = new AppFeedbackVO();
        updateFeedBack.setFeedBackIdList(feedBackIdList);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateFeedBack, loginAccount);
        int result = feedBackDao.delete(updateFeedBack);
        if(result != 1){
            throw new WkRentException("删除用户反馈失败，用户反馈信息已被删除！");
        }
    }

    /**
     * 根据feedBackId查询前台用户反馈信息
     *
     * @param feedBackId 前台用户反馈id
     * @return 未被删除user
     */
    @Override
    public BaseAjaxVO findByFeedId(String feedBackId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(feedBackId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("用户反馈Id不能为空");
            return baseAjaxVO;
        }
        AppFeedback feedBack = feedBackDao.findById(feedBackId);
        if(feedBack == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("用户反馈不存在或已被删除！");
            return baseAjaxVO;
        }
        baseAjaxVO.setResult(BeanUtil.copyBean(feedBack, AppFeedbackVO.class));
        return baseAjaxVO;
    }

    @Override
    public void manage(AppFeedbackVO feedbackVO, String loginAccount) {
        if(StringUtils.isBlank(feedbackVO.getAppFeedbackId())){
            throw new WkRentException("用户反馈Id不能为空");
        }
        if(AppFeedBackStatusEnum.getByCode(feedbackVO.getAppFeedbackStatus()) == null){
            throw new WkRentException("用户反馈状态有误！");
        }
        AppFeedback appFeedback = new AppFeedback();
        appFeedback.setAppFeedbackId(feedbackVO.getAppFeedbackId());
        appFeedback.setAppFeedbackStatus(feedbackVO.getAppFeedbackStatus());
        appFeedback.setDescription(feedbackVO.getDescription());
        feedBackDao.updateStatus(appFeedback);
    }

}
