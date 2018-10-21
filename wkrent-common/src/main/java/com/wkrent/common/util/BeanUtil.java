package com.wkrent.common.util;

import com.wkrent.common.exception.WkRentException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanUtil
{
    public static void copyBean(Object source, Object target, boolean ignoreSourceNullField)
    {
        String[] sourceNullPropertyNames = null;
        if (ignoreSourceNullField) {
            sourceNullPropertyNames = getSourceNullPropertyNames(source);
        }
        try
        {
            BeanUtils.copyProperties(source, target, sourceNullPropertyNames);
        }
        catch (Exception e)
        {
            throw new WkRentException("拷贝对象异常", e);
        }
    }

    public static void copyBean(Object source, Object target)
    {
        copyBean(source, target, false);
    }

    public static <T> T copyBean(Object source, Class<T> targetClass)
    {
        return (T)copyBean(source, targetClass, false);
    }

    public static <T> T copyBean(Object source, Class<T> targetClass, boolean ignoreSourceNullField)
    {
        T target = null;
        try
        {
            target = targetClass.newInstance();
            String[] sourceNullPropertyNames = null;
            if (ignoreSourceNullField) {
                sourceNullPropertyNames = getSourceNullPropertyNames(source);
            }
            BeanUtils.copyProperties(source, target, sourceNullPropertyNames);
        }
        catch (Exception e)
        {
            throw new WkRentException("拷贝对象异常", e);
        }
        return target;
    }

    public static <T> List<T> copyList(List<?> dataList, Class<T> clazz, boolean ignoreSourceNullField)
    {
        ArrayList<T> newList = new ArrayList();
        try
        {
            if (CollectionUtils.isEmpty(dataList)) {
                return newList;
            }
            for (Object data : dataList)
            {
                T target = clazz.newInstance();
                String[] sourceNullPropertyNames = null;
                if (ignoreSourceNullField) {
                    sourceNullPropertyNames = getSourceNullPropertyNames(data);
                }
                BeanUtils.copyProperties(data, target, sourceNullPropertyNames);
                newList.add(target);
            }
        }
        catch (Exception e)
        {
            throw new WkRentException("bean list 转换异常", e);
        }
        return newList;
    }

    public static <T> List<T> copyList(List<?> dataList, Class<T> clazz)
    {
        return copyList(dataList, clazz, false);
    }

    private static String[] getSourceNullPropertyNames(Object source)
    {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> nullValueFieldNames = new HashSet();
        PropertyDescriptor[] arrayOfPropertyDescriptor1;
        int j = (arrayOfPropertyDescriptor1 = pds).length;
        for (int i = 0; i < j; i++)
        {
            PropertyDescriptor pd = arrayOfPropertyDescriptor1[i];
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                nullValueFieldNames.add(pd.getName());
            }
        }
        String[] result = new String[nullValueFieldNames.size()];
        return (String[])nullValueFieldNames.toArray(result);
    }
}
