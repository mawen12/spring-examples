package com.mawen.predicate;

/**
 * AOP Target Class Filter Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class TargetClassFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String echoServiceClassName = "com.mawen.predicate.TargetClassFilterDemo$EchoService";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = classLoader.loadClass(echoServiceClassName);
        System.out.println(targetClass.getName());
    }

    interface EchoService {

        String echo(String message);
    }

}
