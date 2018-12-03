package com.wkrent.business.app.base.obj;

import java.io.Serializable;
import java.util.List;

public class RegionItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -468015670900769155L;
	
	//首字母
	private String index;
	
	//首字母城市名
	private List<String> items;

	public RegionItem() {
		super();
	}

	public RegionItem(String index, List<String> items) {
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

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	
}
