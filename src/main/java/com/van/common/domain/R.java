package com.van.common.domain;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.van.common.enumerate.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok(T data) {
        return new R<>(ResultCode.SUCCESS,data);
    }

    public static <T> R<T> ok() {
        return new R<>(ResultCode.SUCCESS,null);
    }

    public static <T> R<T> error() {
        return new R<>(ResultCode.SERVER_ERROR,null);
    }

    public static <T> R<T> error(ResultCode resultCode) {
        return new R<>(resultCode,null);
    }


    public R(ResultCode resultCode, T data){
        this.code = resultCode.getCode();
        this.msg =resultCode.getMsg();
        this.data = data;
    }



    public R<T> msg(String msg){
        this.msg=msg;
        return this;
    }

    public R<T> code(Integer code){
        this.code=code;
        return this;
    }

    public R<T> data(T data){
        this.data=data;
        return this;
    }


    public String toJson(){
        return JSONUtil.parse(this).toJSONString(4);
    }

}
