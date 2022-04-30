package com.van.handler;




import com.van.common.domain.R;
import com.van.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wan
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R<Object> customErrorHandle(CustomException e){
        log.error("自定义异常捕获==>{}",e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).msg(e.getMsg());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public R<Object> user(BadCredentialsException e){
        return R.error().msg("用户名或密码错误");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<Object> error(Exception e){
        log.error("系统异常捕获==>{}",e.getMessage());
        e.printStackTrace();
        return R.error().msg(e.getMessage());
    }

}
