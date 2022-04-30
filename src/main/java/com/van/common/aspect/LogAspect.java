package com.van.common.aspect;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;

import com.van.common.anno.WebLog;
import com.van.module.log.domain.Log;
import com.van.module.log.service.LogService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author wan
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private LogService logService;

    private static final String UNKNOWN = "unknown";
    /**
     * 切入点
     */
    @Pointcut("execution(public * com.van.module.*.controller..*.*(..)) && @annotation(com.van.common.anno.WebLog)")
    public void log() {}

    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {


        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        final Log l = Log.builder()
                .ip(getIp(request))
                .url(request.getRequestURI())
                .param(JSONUtil.toJsonStr(getNameAndValue(point)))
                .httpMethod(request.getMethod())
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(header)
                .content(getContent(point))
//                .operator(getIsNoUser(point)?"未知":getOperator())
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString()).build();

        log.debug("http请求 ==> {}: {} {} cost={} param={}",
                l.getIp(),l.getHttpMethod(),l.getUrl(),l.getTimeCost(),l.getParam());
        logService.save(l);
        return result;
    }

//    private String getOperator() {
//        return SecurityUtils.me().map(UserDetail::getUsername).orElse("未知");
//    }

    private boolean getIsNoUser(ProceedingJoinPoint point){
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        WebLog webLog = methodSignature.getMethod().getAnnotation(WebLog.class);
        boolean flag = false;
        if (webLog!=null){
            flag = webLog.noUser();
        }
        return flag;
    }

    private String getContent(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        WebLog webLog = methodSignature.getMethod().getAnnotation(WebLog.class);
        String content="";
        if (webLog!=null){
            content = webLog.value();
        }
        return content;
    }

    /**
     *  获取方法参数名和参数值
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = MapUtil.newHashMap();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }


}
