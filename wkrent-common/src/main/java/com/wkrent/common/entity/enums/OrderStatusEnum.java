package com.wkrent.common.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum OrderStatusEnum {

    /**
     * 房源状态
     */
    ORDERING("1", "预约中"),
    ORDER_CANCEL("2", "已取消预约"),
    LOCKED("3", "房源锁定"),
    ROOM_RENTED("4", "房屋已出租"),
    SIGN_CONTRACT("5", "待签合同"),
    FINISH("6", "已完成"),
    SOLD_OUT("7", "房源已下架"),
    ;

    private static Map<String, OrderStatusEnum> enum_map;
    private String code;
    private String desc;

    static
    {
        enum_map = new HashMap();
        OrderStatusEnum[] arrayOfRoomStatusEnum;
        int j = (arrayOfRoomStatusEnum = values()).length;
        for (int i = 0; i < j; i++)
        {
            OrderStatusEnum as = arrayOfRoomStatusEnum[i];
            enum_map.put(as.code, as);
        }
    }

    public static OrderStatusEnum getByCode(String code)
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

    private OrderStatusEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
