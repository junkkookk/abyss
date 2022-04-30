package com.van.module.auth.service.impl;

import com.van.module.auth.domain.Role;
import com.van.module.auth.mapper.RoleMapper;
import com.van.module.auth.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
