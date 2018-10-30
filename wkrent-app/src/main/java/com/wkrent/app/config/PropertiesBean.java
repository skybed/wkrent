package com.wkrent.app.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.wkrent.common.util.PropertiesUtils;

/**
 * 读取系统配置文件
 */
public class PropertiesBean extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

		super.processProperties(beanFactoryToProcess, props);
		Map<String, String> resolvePrperties = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			resolvePrperties.put(keyStr, props.getProperty(keyStr));
		}
		PropertiesUtils.setProperties(resolvePrperties);
	}

}
