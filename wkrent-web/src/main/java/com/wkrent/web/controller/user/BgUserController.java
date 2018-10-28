package com.wkrent.web.controller.user;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.bg.usermanagement.service.BgUserService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgUserVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Api
@Controller
@RequestMapping("/bgUser")
public class BgUserController extends BaseController {

    @Autowired
    private BgUserService bgUserService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllUser(HttpServletRequest request) {
        return JSON.toJSONString(bgUserService.getAllUser());
    }

    @ApiOperation(value = "条件查询用户信息", notes = "条件查询用户信息")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgUserVO> findByCondition(@RequestBody BgUserVO bgUserVO){
        return bgUserService.findByCondition(bgUserVO);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody BgUserVO bgUserVO, HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            baseAjaxVO = bgUserService.insert(bgUserVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增用户失败", e, bgUserVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增平台账户失败，系统异常", e, bgUserVO);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody BgUserVO bgUserVO, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.update(bgUserVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新用户信息失败", e, bgUserVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新用户信息失败，系统异常", e, bgUserVO);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody String userId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.delete(userId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除用户失败", e, userId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除用户失败，系统异常", e, userId);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/lockAccount", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO lockAccount(@RequestBody String userId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.lockAccount(userId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("锁定用户失败", e, userId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("锁定用户失败，系统异常", e, userId);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/unlockAccount", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO unlockAccount(@RequestBody String userId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.unlockAccount(userId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("解锁用户失败", e, userId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("解锁用户失败，系统异常", e, userId);
        }
        return baseAjaxVO;
    }
}
