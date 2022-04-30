package com.van.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.van.common.domain.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author junkkook
 */
public class RespUtils {

    public static void out(HttpServletResponse response, R<Object> r){
        ObjectMapper mapper = new ObjectMapper();
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
