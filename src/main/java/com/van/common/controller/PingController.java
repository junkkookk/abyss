package com.van.common.controller;

import com.van.common.domain.R;
import com.van.common.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wan
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public R<?> ping(){
        return R.ok("hello world");
    }

    @GetMapping("/ping/error/{type}")
    public R<?> error(@PathVariable Integer type){
        if (type.equals(1)){
            throw new CustomException("自定义错误");
        }else {
            throw new RuntimeException("系统错误");
        }
    }
}
