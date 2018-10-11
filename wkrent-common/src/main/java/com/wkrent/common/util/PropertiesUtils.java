package com.wkrent.common.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class PropertiesUtils {

	/** 资源文件中配置的信息 */
	private static Map<String, String> properties;

	/**
	 * 设置资源文件中的配置信息
	 * @param properties
	 */
	public static void setProperties(Map<String, String> properties) {
		PropertiesUtils.properties = properties;
	}

	/**
	 * 根据KEY取得资源配置信息 获取不到返回空
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		String result = properties.get(key);
		if (StringUtils.isEmpty(result)) {
			result = "";
		}
		return result.trim();
	}

	/**
	 * 根据KEY取得资源配置信息 获取不到返回value
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getProperty(String key, String value) {
		String result = properties.get(key);
		if (StringUtils.isEmpty(result)) {
			return value;
		}
		return result.trim();
	}

}
