package com.van.module.auth.service.impl;


import com.van.module.auth.domain.LoginDTO;
import com.van.module.auth.domain.UserDetail;
import com.van.module.auth.service.AuthService;
import com.van.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author wan
 */
@Service
public class DaoAuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDTO param) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        return this.generateTokenStr(authentication);
    }

    public String generateTokenStr(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        return JwtUtils.createToken(userDetails.getUsername());
    }
}
