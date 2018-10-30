package com.wkrent.web.controller.role;

import com.wkrent.business.bg.rolemanagement.service.BgRoleService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgRoleVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "bgRole", tags = "角色接口")
@Controller
@RequestMapping("/bgRole")
public class BgRoleController extends BaseController {

    @Autowired
    private BgRoleService bgRoleService;


    @ApiOperation(value = "条件查询后台角色", notes = "条件查询后台角色", httpMethod = "POST", response = BgRoleVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgRoleVO> findByCondition(@RequestBody BgRoleVO bgRoleVO){
        return bgRoleService.findByCondition(bgRoleVO);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody String roleId, @ApiIgnore HttpSession session){
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
    @ResponseBody
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
    @ResponseBody
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
