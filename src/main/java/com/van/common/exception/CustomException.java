package com.van.common.exception;



import com.van.common.enumerate.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException{
    private Integer code;
    private String msg;

    public CustomException(ResultCode errorResult){
        super(errorResult.getMsg());
        this.code = errorResult.getCode();
        this.msg = errorResult.getMsg();
    }

    public CustomException(String msg){
        super(msg);
        this.code = ResultCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }

}
