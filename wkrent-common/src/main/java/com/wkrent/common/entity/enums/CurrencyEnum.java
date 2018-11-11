package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum CurrencyEnum {

    /**
     * 币种
     */
    CNY("0", "人民币"),
    POUND("1", "英镑"),
    ;

    private static Map<String, CurrencyEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        CurrencyEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            CurrencyEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static CurrencyEnum getByCode(String code)
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

    private CurrencyEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
