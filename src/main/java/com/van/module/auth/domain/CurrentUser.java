package com.van.module.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author wan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {

    private String username;

    private Collection<GrantedAuthority> grantedAuthorities;

}
