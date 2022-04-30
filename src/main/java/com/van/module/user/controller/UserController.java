package com.van.module.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.van.common.anno.WebLog;
import com.van.common.domain.R;
import com.van.module.user.service.UserService;
import com.van.module.user.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @author wan
*/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @WebLog("根据ID查询用户")
    @GetMapping("{id}")
    public R<?> getUserById(@PathVariable Long id){
        return R.ok(userService.getById(id));
    }

    @WebLog("分页获取用户")
    @PostMapping
    public R<?> getUsers(@RequestParam Integer current,@RequestParam Integer pageSize){
        return R.ok(userService.page(new Page<>(current,pageSize)));
    }

    @WebLog("添加用户")
    @PutMapping
    public R<?> addUser(@RequestBody User user){
        userService.save(user);
        return R.ok();
    }

    @WebLog("删除用户")
    @DeleteMapping("{id}")
    public R<?> deleteUser(@PathVariable Long id){
        userService.removeById(id);
        return R.ok();
    }

    @WebLog("更新用户")
    @PatchMapping
    public R<?> updateUser(@RequestBody User user){
        userService.updateById(user);
        return R.ok();
    }


}

