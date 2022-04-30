package com.van.common.controller;

import com.van.common.enumerate.ResultCode;
import com.van.common.exception.CustomException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wan
 */
@Controller
public class CustomErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error" ;

    @RequestMapping(ERROR_PATH)
    public void errorPathHandler(HttpServletResponse response) {
        throw new CustomException(ResultCode.parse(response.getStatus()));
    }

}
