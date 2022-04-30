package com.van.module.auth.domain;

import lombok.Data;

/**
 * @author wan
 */
@Data
public class LoginDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
