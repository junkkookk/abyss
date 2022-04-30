package com.van.util;

import cn.hutool.core.util.ObjectUtil;
import com.van.common.exception.CustomException;
import com.van.module.auth.domain.CurrentUser;
import com.van.module.auth.domain.UserDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author wan
 */
public class AuthUtils {


    public static boolean isNoLogin() {
        return SecurityContextHolder.getContext().getAuthentication()==null;
    }

    public static void setLogin(Authentication token) {
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public static Optional<CurrentUser> me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ObjectUtil.isEmpty(authentication) && (authentication.getPrincipal() instanceof UserDetail)){
            return Optional.of(((UserDetail) authentication.getPrincipal()).toDisplay());
        }else {
            throw new CustomException("token已过期");
        }
    }

    public static void logout(){
        SecurityContextHolder.clearContext();
    }
}
