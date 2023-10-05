package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object logAspect(ProceedingJoinPoint pjp) throws Throwable {
        Long beganTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        Long endTime = System.currentTimeMillis();

        String token = request.getHeader("token");
        Integer operateId = (Integer) JwtUtils.parseJWT(token).get("id");

        LocalDateTime operateTime = LocalDateTime.now();

        String className = pjp.getTarget().getClass().getName();

        String methodName = pjp.getSignature().getName();

        String methodParams = Arrays.toString(pjp.getArgs());

        String returnValue = JSONObject.toJSONString(proceed);

        Long costTime = endTime - beganTime;

        operateLogMapper.insert(new OperateLog(null,operateId,operateTime,className,methodName,methodParams,returnValue,costTime));
        return proceed;
    }
}
