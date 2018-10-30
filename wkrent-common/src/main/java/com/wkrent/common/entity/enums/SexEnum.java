package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum SexEnum
{
    //男
    MALE("M", "男"),

    //女
    FEMALE("F", "女");

    private static Map<String, SexEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        SexEnum[] arrayOfSexEnum;
        int j = (arrayOfSexEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            SexEnum as = arrayOfSexEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static SexEnum getByCode(String code)
    {
        return (SexEnum)enum_map.get(code);
    }

    public String getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }

    private SexEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}

