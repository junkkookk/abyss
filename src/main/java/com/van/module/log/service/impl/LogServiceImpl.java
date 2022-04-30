package com.van.module.log.service.impl;

import com.van.module.log.domain.Log;
import com.van.module.log.mapper.LogMapper;
import com.van.module.log.service.LogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
