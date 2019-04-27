package com.wkrent.web.controller.question;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.bg.question.service.BgQuestionCategoryService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgQuestionCategoryVO;
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
import java.util.List;

/**
 * @author Administrator
 */
@Api(value = "questionCategory", tags = "问题分类")
@Controller
@RequestMapping("/questionCategory")
public class QuestionCategoryController extends BaseController{

    @Autowired
    private BgQuestionCategoryService bgQuestionCategoryService;

    @ApiOperation(value = "条件查询问题分类信息", notes = "条件查询问题分类信息", httpMethod = "POST", response = BgQuestionCategoryVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgQuestionCategoryVO> findByCondition(@RequestBody @ApiParam(name = "questionCategoryVO", value = "问题分类查询条件")
                                                         BgQuestionCategoryVO questionCategoryVO){
        return bgQuestionCategoryService.findByCondition(questionCategoryVO);
    }

    @ApiOperation(value = "查询所有未删除问题分类信息", notes = "查询所有未删除问题分类信息", httpMethod = "POST", response = BgQuestionCategoryVO.class)
    @RequestMapping(value = "/queryCategoryList", method = RequestMethod.POST)
    @ResponseBody
    public List<BgQuestionCategoryVO> queryCategoryList(){
        return bgQuestionCategoryService.queryCategoryList();
    }

    @ApiOperation(value = "根据id查询问题分类信息", notes = "根据id查询问题分类信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findById(@RequestBody @ApiParam(name = "bgQuestionCatId", value = "问题分类id(仅传入bgQuestionCatId即可)")
                                           BgQuestionCategoryVO questionCategoryVO){
        return bgQuestionCategoryService.findByCategoryId(questionCategoryVO.getBgQuestionCatId());
    }

    /**
     * 新增问题分类信息
     * @param questionCategoryVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "新增问题分类信息", notes = "新增问题分类信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "questionCategoryVO", value = "新增问题分类信息")
                                     BgQuestionCategoryVO questionCategoryVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房源标签
            bgQuestionCategoryService.insert(questionCategoryVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增问题分类失败", e, JSON.toJSON(questionCategoryVO));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增问题分类失败，系统异常", e, JSON.toJSON(questionCategoryVO));
        }
        return baseAjaxVO;
    }

    /**
     * 修改问题分类信息
     * @param questionCategoryVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "修改问题分类信息", notes = "修改问题分类信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "questionCategoryVO", value = "修改问题分类信息")
                                         BgQuestionCategoryVO questionCategoryVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房源标签
            bgQuestionCategoryService.update(questionCategoryVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新问题分类失败", e, JSON.toJSON(questionCategoryVO));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新问题分类失败，系统异常", e, JSON.toJSON(questionCategoryVO));
        }
        return baseAjaxVO;
    }

    /**
     * 修改问题分类排序
     * @param questionCategoryVOList 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "修改问题分类排序信息", notes = "修改问题分类排序信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/updateCategoryIndex", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO updateCategoryIndex(@RequestBody @ApiParam(name = "questionCategoryVOList", value = "修改问题分类排序")
                                                  List<BgQuestionCategoryVO> questionCategoryVOList, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房源标签
            bgQuestionCategoryService.updateCategoryIndex(questionCategoryVOList, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新问题分类排序信息失败", e, JSON.toJSON(questionCategoryVOList));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新问题分类排序信息失败，系统异常", e, JSON.toJSON(questionCategoryVOList));
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "删除问题分类信息", notes = "删除问题分类信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "bgQuestionCatIdList", value = "分类idList(传入bgQuestionCatIdList)")
                                     BgQuestionCategoryVO questionCategoryVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgQuestionCategoryService.delete(questionCategoryVO.getBgQuestionCatIdList(), getLoginAccount(session), baseAjaxVO);
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除问题分类信息失败", e, JSON.toJSON(questionCategoryVO));
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除问题分类信息失败，系统异常", e, JSON.toJSON(questionCategoryVO));
        }
        return baseAjaxVO;
    }
}
