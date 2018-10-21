package com.wkrent.web.system.controller;

import com.wkrent.business.bguser.manager.service.BgUserService;
import com.wkrent.common.entity.po.BgUser;
import com.wkrent.common.util.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
public class SystemController {

    private final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Resource
    private BgUserService bgUserService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home() {
        log.info("返回首页！");
        return "index";
    }

    @RequestMapping(value = "/initLogin",method = RequestMethod.GET)
    public String initLogin() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam String userAccount, @RequestParam String password) {
        BgUser user = bgUserService.findByUserAccount(userAccount);
        if(user!=null){
            if(user.getBgUserPwd().equals(Md5Utils.encryptPassword(userAccount, password, Md5Utils.SALT))){
                request.getSession().setAttribute("userId", user.getBgUserId());
                request.getSession().setAttribute("userAccount", userAccount);
                return "index";
            }else{
                log.info("密码错误");
                request.getSession().setAttribute("message", "用户名密码错误，请重新登录");
                return "login";
            }
        }else{
            log.info("用户名不存在");
            request.getSession().setAttribute("message", "用户名不存在，请重新登录");
            return "login";
        }
    }
}
