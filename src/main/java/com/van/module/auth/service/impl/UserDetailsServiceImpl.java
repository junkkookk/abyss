package com.van.module.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.van.common.enumerate.ResultCode;
import com.van.module.auth.domain.Role;
import com.van.module.auth.domain.UserDetail;
import com.van.module.auth.domain.UserRole;
import com.van.module.auth.service.RoleService;
import com.van.module.auth.service.UserRoleService;
import com.van.module.user.domain.User;
import com.van.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper= new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User dbUser = userService.getOne(wrapper);
        if (ObjectUtil.isEmpty(dbUser)){
            throw new UsernameNotFoundException(ResultCode.NOT_FOUND.getMsg());
        }
        List<String> roleList= new ArrayList<>();
        userRoleService.lambdaQuery()
                .eq(UserRole::getUserId,dbUser.getId())
                .list()
                .stream()
                .map(UserRole::getRoleId)
                .forEach(roleId->{
                    Role role = roleService.getById(roleId);
                    if (ObjectUtil.isNotEmpty(role)){
                        roleList.add(role.getCode());
                    }
                });
        List<String> authorities= new ArrayList<>();
        authorities.add("test");
        roleList.addAll(authorities);
        return UserDetail
                .builder()
                .user(dbUser)
                .grantedAuthorities(AuthorityUtils.createAuthorityList(String.valueOf(roleList)))
                .build();
    }
}
