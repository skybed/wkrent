package com.wkrent.business.app.base.obj;

import java.io.Serializable;

public class DataDict implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -865055714734665000L;

	//字典类型值id，主键
	private String dataDictId;
	
	//字典类型值名
	private String dataDictName;

	public DataDict() {
		super();
	}

	public DataDict(String dataDictId, String dataDictName) {
		super();
		this.dataDictId = dataDictId;
		this.dataDictName = dataDictName;
	}

	public String getDataDictId() {
		return dataDictId;
	}

	public void setDataDictId(String dataDictId) {
		this.dataDictId = dataDictId;
	}

	public String getDataDictName() {
		return dataDictName;
	}

	public void setDataDictName(String dataDictName) {
		this.dataDictName = dataDictName;
	}
	
}
