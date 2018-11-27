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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Api(value = "bgUser", tags = "后台用户（平台账号接口）")
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
    public PageResult<BgUserVO> findByCondition(@RequestBody @ApiParam(name = "bgUserVO", value = "查询条件")
                                                            BgUserVO bgUserVO){
        return bgUserService.findByCondition(bgUserVO);
    }

    @ApiOperation(value = "新增平台账号信息", notes = "新增平台账号信息")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "bgUserVO", value = "待新增数据")
                                         BgUserVO bgUserVO, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "修改平台账号信息", notes = "修改平台账号信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "bgUserVO", value = "待修改账号")
                                         BgUserVO bgUserVO, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "修改平台账号密码", notes = "修改平台账号密码", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO updatePassWord(@RequestBody @ApiParam(name = "bgUserVO", value = "待修改账号密码（bgUserId，bgUserPwd）")
                                         BgUserVO bgUserVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.updatePassWord(bgUserVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("修改平台账号密码失败", e, bgUserVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("修改平台账号密码失败，系统异常", e, bgUserVO);
        }
        return baseAjaxVO;
    }
//
//    @ApiOperation(value = "根据userId查询用户信息", notes = "根据userId查询用户信息", httpMethod = "POST", response = BaseAjaxVO.class)
//    @RequestMapping(value = "/findById", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseAjaxVO findById(@RequestBody @ApiParam(name = "bgUserVO", value = "用户id bgUserId")
//                                     BgUserVO bgUserVO, @ApiIgnore HttpSession session){
//        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
//        try {
//            bgUserService.update(bgUserVO, getLoginAccount(session));
//        }catch (WkRentException e){
//            baseAjaxVO.setCode(Constants.FAILED_CODE);
//            baseAjaxVO.setText(e.getMessage());
//            log.warn("根据userId查询用户信息失败", e, bgUserVO);
//        }catch (Exception e){
//            baseAjaxVO.setCode(Constants.FAILED_CODE);
//            baseAjaxVO.setText(Constants.FAILED_TEXT);
//            log.error("根据userId查询用户信息失败，系统异常", e, bgUserVO);
//        }
//        return baseAjaxVO;
//    }

    @ApiOperation(value = "删除平台账号信息", notes = "删除平台账号信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "userId", value = "账号id(仅传入bgUserId即可)")
                                         BgUserVO bgUserVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.delete(bgUserVO.getBgUserId(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除用户失败", e, bgUserVO.getBgUserId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除用户失败，系统异常", e, bgUserVO.getBgUserId());
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "锁定平台账号信息", notes = "锁定平台账号信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/lockAccount", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO lockAccount(@RequestBody @ApiParam(name = "userId", value = "账号id(仅传入bgUserId即可)")
                                              BgUserVO bgUserVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.lockAccount(bgUserVO.getBgUserId(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("锁定用户失败", e, bgUserVO.getBgUserId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("锁定用户失败，系统异常", e, bgUserVO.getBgUserId());
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "解锁平台账号信息", notes = "解锁平台账号信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/unlockAccount", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO unlockAccount(@RequestBody @ApiParam(name = "userId", value = "账号id(仅传入bgUserId即可)")
                                                BgUserVO bgUserVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgUserService.unlockAccount(bgUserVO.getBgUserId(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("解锁用户失败", e, bgUserVO.getBgUserId());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("解锁用户失败，系统异常", e, bgUserVO.getBgUserId());
        }
        return baseAjaxVO;
    }
}
