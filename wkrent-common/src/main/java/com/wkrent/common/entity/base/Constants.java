package com.wkrent.common.entity.base;

/**
 * @author Administrator
 * 系统常量
 */
public interface Constants {

    int SUCCESS_CODE = 0;

    int FAILED_CODE = -1;

    int TOKEN_AUTH_FAILED = -999;

    String SUCCESS_TEXT = "操作成功";

    String FAILED_TEXT = "操作失败，系统异常！";

    String TOKEN_AUTH_FAILED_TEXT = "token已失效，请重新登录！";

    String STR_TRUE = "1";

    String STR_FALSE = "0";

    /**
     * 数据字典类型-房源标签
     */
    String DICT_TYPE_HOUSE_LABEL = "房源标签";

    /**
     * 数据字典类型-房屋类型
     */
    String DICT_TYPE_HOUSE_TYPE = "房屋类型";

    /**
     * 数据字典类型-房屋性质
     */
    String DICT_TYPE_HOUSE_PROPERTY = "房屋性质";
}
