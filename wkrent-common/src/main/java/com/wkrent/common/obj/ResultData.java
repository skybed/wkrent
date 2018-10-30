package com.wkrent.common.obj;

import java.io.Serializable;

public class ResultData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -616037736221548722L;
	
	//状态码
	private String code;
	
	//操作返回消息
	private String msg;
	
	//数据
	private String data;

	public ResultData() {
		super();
	}

	public ResultData(String code, String msg, String data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
