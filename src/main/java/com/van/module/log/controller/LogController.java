package com.van.module.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.van.common.anno.WebLog;
import com.van.common.domain.R;
import com.van.module.log.service.LogService;
import com.van.module.log.domain.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @author wan
*/
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @WebLog("查看日志")
    @GetMapping("{id}")
    public R<?> getLogById(@PathVariable Long id){
        return R.ok(logService.getById(id));
    }

    @WebLog("分页查看日志")
    @PostMapping
    public R<?> getLogs(@RequestParam Integer current,@RequestParam Integer pageSize){
        return R.ok(logService.page(new Page<>(current,pageSize)));
    }

}

