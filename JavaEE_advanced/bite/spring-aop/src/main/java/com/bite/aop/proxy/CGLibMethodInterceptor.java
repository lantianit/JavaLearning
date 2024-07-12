package com.bite.aop.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class CGLibMethodInterceptor implements MethodInterceptor {
    private Object target;

    public CGLibMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 调用目标方法, 并对目标方法进行功能增强
     * @param o  代理类
     * @param method  目标方法
     * @param objects 参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("CGLib动态代理开始");
        //调用目标方法
        Object result = method.invoke(target, objects);
        log.info("CGLib 动态代理结束");
        return result;
    }
}
