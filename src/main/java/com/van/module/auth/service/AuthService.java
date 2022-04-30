package com.van.module.auth.service;

import com.van.module.auth.domain.LoginDTO;

/**
 * @author wan
 */
public interface AuthService {
    String login(LoginDTO loginDTO);
}
