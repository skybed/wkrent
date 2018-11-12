package com.wkrent.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomNumUtil {

	/**
     * 生成随机数 用于手机验证码
     * @param num 位数
     * @return
     */
	public static String getRandomNum(int num) {
    	String randomNumStr = "";
    	for(int i = 0; i < num;i ++){
    		int randomNum = (int)(Math.random() * 10);
    		randomNumStr += randomNum;
    	}
    	return randomNumStr;
	}
	
	/**
	 * 生成编号
	 * @return
	 */
	public static String getRandomNum() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(new Date()) + getRandomNum(2);
	}
	
}
