package com.wkrent.web.bgrole.manager.controller;

import com.wkrent.business.bgrole.manager.service.BgRoleService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgRoleVO;
import com.wkrent.common.exception.WkRentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/bgRole")
public class BgRoleController extends BaseController {

    @Autowired
    private BgRoleService bgRoleService;


    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    public PageResult<BgRoleVO> findByCondition(@RequestBody BgRoleVO bgRoleVO){
        return bgRoleService.findByCondition(bgRoleVO);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseAjaxVO insert(@RequestBody BgRoleVO bgRoleVO, HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            baseAjaxVO = bgRoleService.insert(bgRoleVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增角色失败", e, bgRoleVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增角色失败，系统异常", e, bgRoleVO);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseAjaxVO update(@RequestBody BgRoleVO bgRoleVO, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.update(bgRoleVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新角色信息失败", e, bgRoleVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新角色信息失败，系统异常", e, bgRoleVO);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseAjaxVO delete(@RequestBody String roleId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.delete(roleId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除角色失败", e, roleId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除角色失败，系统异常", e, roleId);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public BaseAjaxVO disable(@RequestBody String roleId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.disable(roleId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("禁用角色失败", e, roleId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("禁用角色失败，系统异常", e, roleId);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public BaseAjaxVO enable(@RequestBody String roleId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.enable(roleId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("启用角色失败", e, roleId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("启用角色失败，系统异常", e, roleId);
        }
        return baseAjaxVO;
    }
}
