package com.wkrent.app.helpcenter.controller;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.bg.question.service.BgQuestionCategoryService;
import com.wkrent.business.bg.question.service.BgQuestionService;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.vo.BgQuestionCategoryVO;
import com.wkrent.common.entity.vo.BgQuestionVO;
import com.wkrent.common.obj.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "order", tags = "帮助中心")
@Controller
@RequestMapping("/app/api")
public class HelpCenterController {

    @Autowired
    private BgQuestionCategoryService bgQuestionCategoryService;

    @Autowired
    private BgQuestionService bgQuestionService;

    /**
     * 查询问题分类信息
     * @param request
     * @return
     */
    @ApiOperation(value = "查询问题分类信息", notes = "查询问题分类信息", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/queryQuestionCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryQuestionCategory(HttpServletRequest request) {

        ResultData resultData = new ResultData();
        resultData.setCode(Constant.RESULT_SUCCESS_CODE);
        resultData.setMsg(Constant.RESULT_SUCCESS_MSG);

        Map<String, Object> map = new HashMap<>(4);
        List<BgQuestionCategoryVO> questionCategoryVOList = bgQuestionCategoryService.queryCategoryList();

        map.put("list", questionCategoryVOList);
        map.put("flag", true);
        resultData.setData(JSON.toJSONString(map));
        return JSON.toJSONString(resultData);
    }



    /**
     * 根据问题分类id查询问题信息
     * @param request
     * @param categoryId 问题分类id
     * @return
     */
    @ApiOperation(value = "根据问题分类id查询问题信息", notes = "根据问题分类id查询问题信息", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/queryQuestionByCatId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryQuestionByCatId(HttpServletRequest request, String categoryId) {
        ResultData resultData = new ResultData();
        resultData.setCode(Constant.RESULT_SUCCESS_CODE);
        resultData.setMsg(Constant.RESULT_SUCCESS_MSG);

        if(StringUtils.isNotEmpty(categoryId)) {
            //查询问题信息
            List<BgQuestionVO> questionVOList = bgQuestionService.queryByCategoryId(categoryId);
            Map<String, Object> map = new HashMap<>(4);
            map.put("list", questionVOList);
            map.put("flag", true);
            resultData.setData(JSON.toJSONString(map));

        } else {
            resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
            resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
        }

        return JSON.toJSONString(resultData);
    }

    /**
     * 根据问题id查询问题信息
     * @param request
     * @param questionId 问题id
     * @return
     */
    @ApiOperation(value = "根据问题id查询问题信息", notes = "根据问题id查询问题信息", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findQuestionById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findQuestionById(HttpServletRequest request, String questionId) {
        ResultData resultData = new ResultData();
        resultData.setCode(Constant.RESULT_SUCCESS_CODE);
        resultData.setMsg(Constant.RESULT_SUCCESS_MSG);

        if(StringUtils.isNotEmpty(questionId)) {
            //查询问题信息
            BaseAjaxVO baseAjaxVO = bgQuestionService.findById(questionId);
            resultData.setData(JSON.toJSONString(baseAjaxVO.getResult()));
        } else {
            resultData.setCode(Constant.RESULT_REQUIRE_PARAM_CODE);
            resultData.setMsg(Constant.RESULT_REQUIRE_PARAM_MSG);
        }
        return JSON.toJSONString(resultData);
    }

}
