package com.wkrent.app.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class Md5Util {
	
	private static byte[] md5(String s) {
        try {
        	MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
        	Logger.getRootLogger().error("MD5 Error throw exception: " + e.getMessage(), e);
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String encryptValue(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
        	Logger.getRootLogger().error("not supported charset throw exception: " + e.getMessage(), e);
            return s;
        }
    }
    
    /**
     * 加密
     * @param value
     * @return
     */
    public static String encrypt(String value) {
        return Md5Util.encryptValue(value);
    }
    
}
