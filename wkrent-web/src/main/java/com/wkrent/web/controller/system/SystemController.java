package com.wkrent.web.controller.system;

import com.wkrent.business.bg.usermanagement.service.BgUserService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.vo.BgMenuVO;
import com.wkrent.common.entity.vo.BgUserVO;
import com.wkrent.common.entity.vo.LoginUserVO;
import com.wkrent.common.util.JwtUtil;
import com.wkrent.common.util.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "初始化登录页面", notes = "初始化登录页面", httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO login(@RequestBody
                            @ApiParam(name = "用户登录信息")
                                    LoginUserVO loginUser) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        String userAccount = loginUser.getUserAccount();
        String password = loginUser.getPassword();
        BgUserVO user = bgUserService.findByUserAccount(userAccount);
        if(user!=null){
            if(user.getBgUserPwd().equals(Md5Utils.encryptPassword(userAccount, password, Md5Utils.SALT))){
                Map<String, Object> resultMap = new HashMap<>();
                //设置当前登录用户菜单信息
                List<BgMenuVO> menuVOList = bgUserService.queryMenuListByUser(user.getBgUserId());
                user.setMenuList(menuVOList);
                resultMap.put("loginUser", user);
                //获取当前登录用户token
                String token = JwtUtil.sign(userAccount, user.getBgUserId());
                if(token != null){
                    resultMap.put("wkToken", token);
                }
                baseAjaxVO.setResult(resultMap);
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
            baseAjaxVO.setText("用户名不存在，请重新输入");
            return baseAjaxVO;
        }
    }

    @RequestMapping(value = "/authFailed",method = RequestMethod.GET)
    @ResponseBody
    public BaseAjaxVO authFailed() {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        baseAjaxVO.setCode(Constants.TOKEN_AUTH_FAILED);
        baseAjaxVO.setText(Constants.TOKEN_AUTH_FAILED_TEXT);
        return baseAjaxVO;
    }
}
