package com.wkrent.web.controller.question;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.bg.question.service.BgQuestionService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgQuestionVO;
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
 * @author Administrator
 */
@Api(value = "question", tags = "问题")
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController{

    @Autowired
    private BgQuestionService bgQuestionService;

    @ApiOperation(value = "条件查询问题信息", notes = "条件查询问题信息", httpMethod = "POST", response = BgQuestionVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgQuestionVO> findByCondition(@RequestBody @ApiParam(name = "questionVO", value = "问题查询条件")
                                                         BgQuestionVO questionVO){
        return bgQuestionService.findByCondition(questionVO);
    }

    @ApiOperation(value = "根据id查询问题信息", notes = "根据id查询问题信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findById(@RequestBody @ApiParam(name = "bgQuestionId", value = "问题id(仅传入bgQuestionId即可)")
                                           BgQuestionVO questionVO){
        return bgQuestionService.findById(questionVO.getBgQuestionId());
    }

    /**
     * 新增问题信息
     * @param questionVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "新增问题信息", notes = "新增问题信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "questionVO", value = "新增问题信息")
                                     BgQuestionVO questionVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房源标签
            bgQuestionService.insert(questionVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增问题失败", e, JSON.toJSON(questionVO));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增问题失败，系统异常", e, JSON.toJSON(questionVO));
        }
        return baseAjaxVO;
    }

    /**
     * 修改问题信息
     * @param questionVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "修改问题信息", notes = "修改问题信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "questionVO", value = "修改问题信息")
                                         BgQuestionVO questionVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgQuestionService.update(questionVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新问题失败", e, JSON.toJSON(questionVO));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新问题失败，系统异常", e, JSON.toJSON(questionVO));
        }
        return baseAjaxVO;
    }


    @ApiOperation(value = "删除问题信息", notes = "删除问题信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "bgQuestionIdList", value = "问题idList(传入bgQuestionIdList)")
                                     BgQuestionVO questionVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgQuestionService.delete(questionVO.getBgQuestionIdList(), getLoginAccount(session), baseAjaxVO);
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除问题信息失败", e, JSON.toJSON(questionVO));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除问题信息失败，系统异常", e, JSON.toJSON(questionVO));
        }
        return baseAjaxVO;
    }
}
