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
@Api(value = "bgRole", tags = "角色接口")
@Controller
@RequestMapping("/bgRole")
public class BgRoleController extends BaseController {

    @Autowired
    private BgRoleService bgRoleService;


    @ApiOperation(value = "条件查询后台角色", notes = "条件查询后台角色", httpMethod = "POST", response = BgRoleVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgRoleVO> findByCondition(@RequestBody
                                                    @ApiParam(name = "bgRoleVO", value = "查询条件")
                                                            BgRoleVO bgRoleVO){
        return bgRoleService.findByCondition(bgRoleVO);
    }

    @ApiOperation(value = "查询所有启用角色信息", notes = "条件查询后台角色", httpMethod = "GET", response = BgRoleVO.class)
    @RequestMapping(value = "/queryRoleInfo", method = RequestMethod.GET)
    @ResponseBody
    public BaseAjaxVO queryRoleInfo(){
        return bgRoleService.queryRoleInfo();
    }

    @ApiOperation(value = "新增角色信息", notes = "新增角色信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "bgRoleVO", value = "新增角色信息")
                                         BgRoleVO bgRoleVO,
                             @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "修改角色信息", notes = "修改角色信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "bgRoleVO", value = "待更新角色信息")
                                         BgRoleVO bgRoleVO, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "删除角色信息", notes = "删除角色信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "roleId", value = "角色Id(仅传入bgRoleId即可)")
                                         BgRoleVO roleVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.delete(roleVO.getBgRoleId(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除角色失败", e, roleVO.getBgRoleId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除角色失败，系统异常", e, roleVO.getBgRoleId());
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "根据角色id查询用户信息", notes = "根据角色id查询用户信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findUserByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findUserByRoleId(@RequestBody @ApiParam(name = "roleId", value = "角色Id(仅传入bgRoleId即可)")
                                         BgRoleVO roleVO){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            baseAjaxVO = bgRoleService.findUserByRoleId(roleVO.getBgRoleId());
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("根据角色id查询用户信息失败", e, roleVO.getBgRoleId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("根据角色id查询用户信息失败，系统异常", e, roleVO.getBgRoleId());
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "禁用角色信息", notes = "禁用角色信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO disable(@RequestBody @ApiParam(name = "roleId", value = "角色Id(仅传入bgRoleId即可)")
                                          BgRoleVO roleVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.disable(roleVO.getBgRoleId(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("禁用角色失败", e, roleVO.getBgRoleId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("禁用角色失败，系统异常", e, roleVO.getBgRoleId());
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "启用角色信息", notes = "启用角色信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO enable(@RequestBody @ApiParam(name = "roleId", value = "角色Id(仅传入bgRoleId即可)")
                                         BgRoleVO roleVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoleService.enable(roleVO.getBgRoleId(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("启用角色失败", e, roleVO.getBgRoleId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("启用角色失败，系统异常", e, roleVO.getBgRoleId());
        }
        return baseAjaxVO;
    }
}
