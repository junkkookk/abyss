package com.van.module.auth.service.impl;

import com.van.module.auth.domain.UserRole;
import com.van.module.auth.mapper.UserRoleMapper;
import com.van.module.auth.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wan
 * @since 2022-04-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
