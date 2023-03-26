package com.mawen.predicate;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * AOP Parameter Filter Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class TargetParameterFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String echoServiceClassName = "com.mawen.predicate.TargetMethodFilterDemo$EchoService";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = classLoader.loadClass(echoServiceClassName);

        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);

        ReflectionUtils.doWithMethods(targetClass,
                new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        System.out.println("Parameter only String Method is: " + method);
                    }
                }, new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        Class[] parameterTypes = method.getParameterTypes();
                        return parameterTypes.length == 1 && String.class.equals(parameterTypes[0]);
                    }
                }
        );
    }

    interface EchoService {

        String echo(String message);
    }
}
