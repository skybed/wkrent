package com.wkrent.common.entity.base;

/**
 * @author Administrator
 * ajax请求返回信息
 */
public class BaseAjaxVO {

    /**
     * 请求结果code： 0 成功； -1 失败
     */
    private Integer code;

    /**
     * 请求结果message
     */
    private String text;

    /**
     * 返回结果
     */
    private Object result;

    public BaseAjaxVO(){
        this.code = Constants.SUCCESS_CODE;
        this.text = Constants.SUCCESS_TEXT;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
