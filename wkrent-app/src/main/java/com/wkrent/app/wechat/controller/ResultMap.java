package com.wkrent.app.wechat.controller;

import java.util.HashMap;
import java.util.Map;

public class ResultMap extends HashMap<String, Object> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7204532939809189554L;

	public static final String SUCCESS = "success";
    
    public static final String EXCEPTION = "exception";
    
    public static final Integer SUCCESSCODE = 0;
        
    public static final Integer EXCEPTIONCODE = 500;
    

    public ResultMap() {
    	put("errCode", 0);
        put("msg", SUCCESS);
    }
    
    public ResultMap(int code, String msg){
    	put("errCode", code);
    	put("msg", msg);
    }

    public static ResultMap error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResultMap error(String msg) {
        return error(500, msg);
    }

    public static ResultMap error(int code, String msg) {
    	ResultMap r = new ResultMap();
        r.put("errCode", code);
        r.put("msg", msg);
        return r;
    }

    public static ResultMap ok(String msg) {
        ResultMap r = new ResultMap();
        r.put("msg", msg);
        return r;
    }

    public static ResultMap ok(Map<String, Object> map) {
        ResultMap r = new ResultMap();
        r.putAll(map);
        return r;
    }

    public static ResultMap ok() {
        return new ResultMap();
    }

    public ResultMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    
}
