package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum UploadFileTypeEnum {

    /**
     * 模块设置附件类型
     */
    CUSTOMER_FILE("10", "客服微信号"),
    PLATFORM_FILE("20", "平台微信号"),
    MERCHANT_FILE("30", "商家附件类型"),
    ROOM_FILE("40", "房源附件类型"),
    ;

    private static Map<String, UploadFileTypeEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        UploadFileTypeEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            UploadFileTypeEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static UploadFileTypeEnum getByCode(String code)
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

    private UploadFileTypeEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
