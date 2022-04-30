package com.van.common.enumerate;

import com.van.common.domain.BaseEnum;
import lombok.Getter;

/**
 * @author wan
 */
@Getter
public enum ResultCode implements BaseEnum {
    NULL(999,"未配置相关返回说明"),
    SUCCESS(200,"请求成功"),
    UNAUTHORIZED(401,"未登录"),
    FORBIDDEN(403,"权限不足"),
    NOT_FOUND(404,"未找到该资源"),
    SERVER_ERROR(500,"服务器内部错误");


    private final Integer code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public static ResultCode parse(Integer code) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }
        }
        return NULL;
    }

    @Override
    public Object getValue() {
        return code;
    }

    @Override
    public String getDesc() {
        return msg;
    }
}
