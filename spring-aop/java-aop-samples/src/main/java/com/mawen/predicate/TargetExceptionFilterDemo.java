package com.mawen.predicate;

import org.apache.tools.ant.taskdefs.rmic.XNewRmic;
import org.springframework.util.ReflectionUtils;

import javax.jws.Oneway;
import java.lang.reflect.Method;

/**
 * AOP Target Exception Filter Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class TargetExceptionFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String echoServiceClassName = "com.mawen.predicate.TargetExceptionFilterDemo$EchoService";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = classLoader.loadClass(echoServiceClassName);

        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);

        ReflectionUtils.doWithMethods(targetClass,
                new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        System.out.println("Throw only NullPointerException Method is: " + method);
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        Class<?>[] exceptionTypes = method.getExceptionTypes();
                        return exceptionTypes.length == 1 && NullPointerException.class.equals(exceptionTypes[0]);
                    }
                }
        );
    }

    interface EchoService {

        String echo(String message) throws NullPointerException;
    }
}
