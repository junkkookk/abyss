package com.van.interceptor;

import com.van.common.constant.SecurityCons;
import com.van.util.AuthUtils;
import com.van.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wan
 * @date 2021/11/26 下午10:44
 */
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtUtils.getTokenFromHeader(request.getHeader(SecurityCons.AUTH_HEADER))
                .flatMap(JwtUtils::getUserNameByToken)
                .ifPresent(
                        username->{
                            if (AuthUtils.isNoLogin()){
                                UserDetails user = userDetailsService.loadUserByUsername(username);
                                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getUsername(), user.getAuthorities());
                                AuthUtils.setLogin(token);
                            }
                        });
        filterChain.doFilter(request,response);
    }


}
