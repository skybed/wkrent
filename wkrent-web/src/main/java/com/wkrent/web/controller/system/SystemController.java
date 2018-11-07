package com.wkrent.web.controller.system;

import com.wkrent.business.bg.usermanagement.service.BgUserService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.po.BgUser;
import com.wkrent.common.util.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Api(value = "system", tags = "系统接口")
@Controller
@RequestMapping("/system")
public class SystemController {

    private final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Resource
    private BgUserService bgUserService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home() {
        log.info("返回首页！");
        return "index";
    }

    @ApiOperation(value = "初始化登录页面", notes = "初始化登录页面", httpMethod = "GET")
    @RequestMapping(value = "/initLogin",method = RequestMethod.GET)
    public String initLogin() {
        return "login";
    }

    @ApiOperation(value = "初始化登录页面", notes = "初始化登录页面", httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseAjaxVO login(@ApiIgnore HttpServletRequest request,
                            @ApiParam(name = "userAccount", value = "平台账号")
                                String userAccount,
                            @ApiParam(name = "password", value = "账号密码")
                                    String password) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        BgUser user = bgUserService.findByUserAccount(userAccount);
        if(user!=null){
            if(user.getBgUserPwd().equals(Md5Utils.encryptPassword(userAccount, password, Md5Utils.SALT))){
                request.getSession().setAttribute("userId", user.getBgUserId());
                request.getSession().setAttribute("userAccount", userAccount);
                return baseAjaxVO;
            }else{
                log.info("密码错误");
                baseAjaxVO.setCode(Constants.FAILED_CODE);
                baseAjaxVO.setText("密码错误，请重新输入");
                return baseAjaxVO;
            }
        }else{
            log.info("用户名不存在");
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("密码错误，请重新输入");
            return baseAjaxVO;
        }
    }
}
