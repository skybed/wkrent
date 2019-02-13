package com.wkrent.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author Administrator
 * uuid生成器
 */
public class UUIDUtil {

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static String getCodeInfo(String startChar, int number){

        StringBuilder buffer = new StringBuilder();
        if(StringUtils.isNotBlank(startChar)){
            buffer.append(startChar);
        }
        buffer.append(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
        Random random = new Random();
        for(int i = 0; i < number; i++){
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }

    public static  void main(String[] args){
        String[] str = getUUID(30);
        for(String s : str){
            System.out.println(s);
        }
    }
}
