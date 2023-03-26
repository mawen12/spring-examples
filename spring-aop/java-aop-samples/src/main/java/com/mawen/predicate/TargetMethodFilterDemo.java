package com.mawen.predicate;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * AOP Target Method Filter Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class TargetMethodFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String echoServiceClassName = "com.mawen.predicate.TargetMethodFilterDemo$EchoService";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = classLoader.loadClass(echoServiceClassName);

        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);


    }

    interface EchoService {

        String echo(String message);
    }
}
