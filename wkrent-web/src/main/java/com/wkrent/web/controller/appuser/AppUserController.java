package com.wkrent.web.controller.appuser;

import com.wkrent.business.bg.appusermanagement.service.BgAppUserService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.AppUserVO;
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
 * 前台用户信息
 * @author Administrator
 */
@Api(value = "appUser", tags = "前台用户接口")
@Controller
@RequestMapping("/appUser")
public class AppUserController extends BaseController{

    @Autowired
    private BgAppUserService bgAppUserService;

    @ApiOperation(value = "条件查询前台用户信息", notes = "条件查询前台用户信息", httpMethod = "POST", response = BgRoomVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<AppUserVO> findByCondition(@RequestBody AppUserVO userVO){
        return bgAppUserService.findByCondition(userVO);
    }

    @ApiOperation(value = "根据id查询前台用户信息", notes = "根据id查询前台用户信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findByUserId(@RequestBody @ApiParam(name = "userId", value = "前台用户id")
                                           String userId){
        return bgAppUserService.findByUserId(userId);
    }

    @ApiOperation(value = "修改前台用户信息", notes = "修改前台用户信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "userVO", value = "待修改前台用户信息")
                                     AppUserVO userVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgAppUserService.update(userVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新前台用户信息失败", e, userVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新前台用户信息失败，系统异常", e, userVO);
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "删除前台用户信息", notes = "删除前台用户信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "userVO", value = "待修改前台用户信息")
                                     AppUserVO userVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgAppUserService.update(userVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除前台用户信息失败", e, userVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除前台用户信息失败，系统异常", e, userVO);
        }
        return baseAjaxVO;
    }
}
