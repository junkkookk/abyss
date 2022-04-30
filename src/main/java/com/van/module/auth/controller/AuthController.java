package com.van.module.auth.controller;

import com.van.common.anno.WebLog;
import com.van.common.domain.R;
import com.van.module.auth.domain.CurrentUser;
import com.van.module.auth.domain.LoginDTO;
import com.van.module.auth.service.AuthService;
import com.van.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wan
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @WebLog("登录")
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginDTO loginDTO){
        return R.ok(authService.login(loginDTO));
    }

    @WebLog("获取个人信息")
    @GetMapping("/me")
    public R<CurrentUser> getMe(){
        return R.ok(AuthUtils.me().orElse(null));
    }

}
