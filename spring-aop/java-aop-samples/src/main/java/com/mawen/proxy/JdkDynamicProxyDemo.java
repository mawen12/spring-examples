package com.mawen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk Dynamic Proxy Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/11
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader,
                new Class[]{EchoService.class},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) { // class match
                    EchoService echoService = new DefaultEchoService();
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        String ret = echoService.echo("Hello,World");
        System.out.println(ret);
    }

    interface EchoService {
        String echo(String message);
    }

    static class DefaultEchoService implements EchoService{

        @Override
        public String echo(String message) {
            return "[ECHO] " + message;
        }
    }
}
