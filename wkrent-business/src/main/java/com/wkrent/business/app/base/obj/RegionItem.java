package com.wkrent.business.app.base.obj;

import java.io.Serializable;

public class RegionItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -468015670900769155L;
	
	//首字母
	private String index;
	
	//首字母城市名
	private String items;

	public RegionItem() {
		super();
	}

	public RegionItem(String index, String items) {
		super();
		this.index = index;
		this.items = items;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
}
