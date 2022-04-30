package com.van.common.enumerate;

import com.van.common.domain.BaseEnum;

/**
 * @author wan
 */
public enum StatusEnum implements BaseEnum {
    ON(1,"开启"),
    OFF(0, "禁用");

    private final Integer code;
    private final String msg;

    StatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Object getValue() {
        return code;
    }

    @Override
    public String getDesc() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
