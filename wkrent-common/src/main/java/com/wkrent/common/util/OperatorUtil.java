package com.wkrent.common.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作人信息工具类
 */
public class OperatorUtil
{
    private static Logger log = LoggerFactory.getLogger(Md5Utils.class);

    public static enum OperationType
    {
        Add,  Update,  Delete;
    }

    public static void setOperatorInfo(OperationType type, Serializable po, String userAccount)
    {
        switch (type)
        {
            case Add:
                setAddOperator(po, userAccount);
                break;
            case Delete:
            case Update:
                setUpdateOperator(po, userAccount);
                break;
        }
    }

    private static void setValue(Object target, String fieldName, Object value)
    {
        try
        {
            FieldUtils.writeDeclaredField(target, fieldName, value, true);
        }
        catch (Exception e)
        {
            log.warn("setValue failed", e);
        }
    }

    public static void setCommonOperator(Serializable po, Date time, String userAccount, boolean insert)
    {

        if (insert)
        {
            setValue(po, "createTime", time);
            setValue(po, "createBy", userAccount);
        }
        setValue(po, "updateBy", userAccount);
        setValue(po, "updateTime", time);
    }

    private static void setAddOperator(Serializable po, String userAccount)
    {
        Date time = new Date();
        setCommonOperator(po, time, userAccount, true);
    }

    private static void setUpdateOperator(Serializable po, String userAccount)
    {
        Date time = new Date();

        setCommonOperator(po, time, userAccount, false);
    }
}

