package com.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class MyCacheAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(com.demo.config.MyCacheable)")
    public Object MyCacheable(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("进入注解片面~~~~~~~~");

        //疑问key的内容-根据方法参数1,获取到方法注解里面的key内容-反射知识
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = proceedingJoinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
        MyCacheable myAnnotation = method.getAnnotation(MyCacheable.class);
        //反射技术-JDK最底层的API

        String keyEL = myAnnotation.key();
        int ttl = myAnnotation.ttl();
        boolean status = myAnnotation.status();


        Expression expression = new SpelExpressionParser().parseExpression(keyEL, new TemplateParserContext());
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = proceedingJoinPoint.getArgs();
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        String key = expression.getValue(context,String.class);
        Object o;
        if (status && ttl > -1) {
            o = redisTemplate.opsForValue().getAndExpire(key, ttl, TimeUnit.SECONDS);
        } else {
            o = redisTemplate.opsForValue().get(key);
        }
        if (o != null) {
            return o;
        }


        Object proceed = proceedingJoinPoint.proceed();

        if (proceed != null) {
            redisTemplate.opsForValue().set(key, proceed, ttl, TimeUnit.SECONDS);
        }
        return proceed;
    }


}

