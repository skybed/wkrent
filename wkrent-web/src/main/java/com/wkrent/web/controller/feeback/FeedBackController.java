package com.wkrent.web.controller.feeback;

import com.wkrent.business.bg.feedbackmanagement.service.FeedBackService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.AppFeedbackVO;
import com.wkrent.common.entity.vo.BgRoomVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * 用户反馈信息
 * @author Administrator
 */
@Api(value = "feedBack", tags = "用户反馈户接口")
@Controller
@RequestMapping("/feedBack")
public class FeedBackController extends BaseController{

    @Autowired
    private FeedBackService feedBackService;


    @ApiOperation(value = "条件查询用户反馈信息", notes = "条件查询用户反馈信息", httpMethod = "POST", response = BgRoomVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<AppFeedbackVO> findByCondition(@RequestBody AppFeedbackVO feedBackVO){
        return feedBackService.findByCondition(feedBackVO);
    }

    @ApiOperation(value = "根据id查询用户反馈信息", notes = "根据id查询用户反馈信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findById(@RequestBody @ApiParam(name = "feedBackId", value = "用户反馈id(仅传入appFeedbackId即可)")
                                           AppFeedbackVO feedbackVO){
        return feedBackService.findByFeedId(feedbackVO.getAppFeedbackId());
    }

    @ApiOperation(value = "初始化运营处理", notes = "初始化运营处理", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/initManage", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO initManage(@RequestBody @ApiParam(name = "feedBackId", value = "用户反馈id(仅传入appFeedbackId即可)")
                                             AppFeedbackVO feedbackVO){
        return feedBackService.findByFeedId(feedbackVO.getAppFeedbackId());
    }

    /**
     * 运营管理
     */
    @ApiOperation(value = "用户反馈运营处理", notes = "用户反馈运营处理", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO manage(@RequestBody @ApiParam(name = "feedBackVO", value = "用户反馈信息")
                                     AppFeedbackVO feedBackVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            feedBackService.manage(feedBackVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("反馈信息运营处理失败", e, feedBackVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("反馈信息运营处理失败，系统异常", e, feedBackVO);
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "删除用户反馈信息", notes = "删除用户反馈信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "feedBackIdList", value = "待删除用户反馈信息(仅传入feedBackIdList即可)")
                                         AppFeedbackVO feedBackVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            feedBackService.delete(feedBackVO.getFeedBackIdList(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除用户反馈信息失败", e, feedBackVO.getFeedBackIdList());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除用户反馈信息失败，系统异常", e, feedBackVO.getFeedBackIdList());
        }
        return baseAjaxVO;
    }
}
