package com.wkrent.common.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Administrator
 */
@ApiModel(description = "用户登录信息")
public class LoginUserVO {

    /**
     * 用户名
     */
    @ApiModelProperty(name = "用户登录账号")
    private String userAccount;

    /**
     * 密码
     */
    @ApiModelProperty(name = "用户登录密码")
    private String password;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
