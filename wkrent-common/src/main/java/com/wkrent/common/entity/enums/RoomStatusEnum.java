package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum RoomStatusEnum {

    /**
     * 房源状态
     */
    RENTING("1", "出租中"),
    RESERVED("2", "已被预订"),
    LOCKED("3", "房源锁定"),
    SIGN_CONTRACT("4", "待签租房合同"),
    RENTED("5", "已出租"),
    SOLD_OUT("6", "已下架")
    ;

    private static Map<String, RoomStatusEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        RoomStatusEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            RoomStatusEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static RoomStatusEnum getByCode(String code)
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

    private RoomStatusEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
