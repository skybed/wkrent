package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum AppFeedBackStatusEnum {

    /**
     * 用户反馈状态
     */
    UNTREATED("0", "未处理"),
    IN_HAND("1", "处理中"),
    PROCESSED("2", "已处理"),
    ;

    private static Map<String, AppFeedBackStatusEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        AppFeedBackStatusEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            AppFeedBackStatusEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static AppFeedBackStatusEnum getByCode(String code)
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

    private AppFeedBackStatusEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
