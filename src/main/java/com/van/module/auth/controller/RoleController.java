package com.van.module.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.van.common.anno.WebLog;
import com.van.common.domain.R;
import com.van.module.auth.service.RoleService;
import com.van.module.auth.domain.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @author wan
*/
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @WebLog("根据ID查询角色")
    @GetMapping("{id}")
    public R<?> getRoleById(@PathVariable Long id){
        return R.ok(roleService.getById(id));
    }

    @WebLog("分页查询角色")
    @PostMapping
    public R<?> getRoles(@RequestParam Integer current,@RequestParam Integer pageSize){
        return R.ok(roleService.page(new Page<>(current,pageSize)));
    }

    @WebLog("新增角色")
    @PutMapping
    public R<?> addRole(@RequestBody Role role){
        roleService.save(role);
        return R.ok();
    }

    @WebLog("删除角色")
    @DeleteMapping("{id}")
    public R<?> deleteRole(@PathVariable Long id){
        roleService.removeById(id);
        return R.ok();
    }

    @WebLog("更新角色")
    @PatchMapping
    public R<?> updateRole(@RequestBody Role role){
        roleService.updateById(role);
        return R.ok();
    }

}

