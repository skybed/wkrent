package com.wkrent.common.exception;

/**
 * @author Administrator
 */
public class WkRentException extends RuntimeException
{
    private static final long serialVersionUID = 4678893385464033511L;
    private int code = -1;

    public WkRentException(String msg, int code)
    {
        super(msg);
        this.code = code;
    }

    public WkRentException(int code)
    {
        this.code = code;
    }

    public WkRentException(String msg)
    {
        super(msg);
    }

    public WkRentException(String msg, Throwable e)
    {
        super(msg, e);
    }

    public WkRentException(Throwable e)
    {
        super(e);
    }

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }
}
