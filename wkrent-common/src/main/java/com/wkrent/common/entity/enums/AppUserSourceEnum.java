package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum AppUserSourceEnum {

    /**
     * 用户反馈状态
     */
    WE_CHART("10", "微信公众号"),
    PC("20", "PC官网"),
    ;

    private static Map<String, AppUserSourceEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        AppUserSourceEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            AppUserSourceEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static AppUserSourceEnum getByCode(String code)
    {
        return enum_map.get(code);
    }

    public String getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }

    private AppUserSourceEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
