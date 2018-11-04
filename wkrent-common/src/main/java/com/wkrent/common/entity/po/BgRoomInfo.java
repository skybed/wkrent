/*
*
* BgRoomInfo.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;

/**
 * 房源附加信息表
 * @author skybed
 *
 */
public class BgRoomInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 79594871712526244L;

    /**
     * 房源信息附加表id,唯一标识
     */
    private String bgRoomInfoId;

    /**
     * 所属房源id
     */
    private String bgRoomId;

    /**
     * 当前预约数
     */
    private Byte bgRoomAppointNum;

    /**
     * 总浏览数
     */
    private Integer bgRoomViewNum;

    /**
     * 
     * @return bg_room_info_id 房源信息附加表id,唯一标识
     */
    public String getBgRoomInfoId() {
        return bgRoomInfoId;
    }

    /**
     * 
     * @param bgRoomInfoId 房源信息附加表id,唯一标识
     */
    public void setBgRoomInfoId(String bgRoomInfoId) {
        this.bgRoomInfoId = bgRoomInfoId == null ? null : bgRoomInfoId.trim();
    }

    /**
     * 
     * @return bg_room_id 所属房源id
     */
    public String getBgRoomId() {
        return bgRoomId;
    }

    /**
     * 
     * @param bgRoomId 所属房源id
     */
    public void setBgRoomId(String bgRoomId) {
        this.bgRoomId = bgRoomId == null ? null : bgRoomId.trim();
    }

    /**
     * 
     * @return bg_room_appoint_num 当前预约数
     */
    public Byte getBgRoomAppointNum() {
        return bgRoomAppointNum;
    }

    /**
     * 
     * @param bgRoomAppointNum 当前预约数
     */
    public void setBgRoomAppointNum(Byte bgRoomAppointNum) {
        this.bgRoomAppointNum = bgRoomAppointNum;
    }

    /**
     * 
     * @return bg_room_view_num 总浏览数
     */
    public Integer getBgRoomViewNum() {
        return bgRoomViewNum;
    }

    /**
     * 
     * @param bgRoomViewNum 总浏览数
     */
    public void setBgRoomViewNum(Integer bgRoomViewNum) {
        this.bgRoomViewNum = bgRoomViewNum;
    }
}