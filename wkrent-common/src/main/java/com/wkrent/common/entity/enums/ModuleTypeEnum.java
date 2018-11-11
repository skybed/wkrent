package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum ModuleTypeEnum {

    /**
     * 模块设置类型
     */
    AGREEMENT("1", "服务协议"),
    RENT_EXPLAIN("20", "租房说明"),
    ;

    private static Map<String, ModuleTypeEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        ModuleTypeEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            ModuleTypeEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static ModuleTypeEnum getByCode(String code)
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

    private ModuleTypeEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
