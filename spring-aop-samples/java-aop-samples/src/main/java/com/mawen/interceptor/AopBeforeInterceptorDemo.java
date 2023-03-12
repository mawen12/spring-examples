package com.mawen.interceptor;

import javax.naming.spi.ObjectFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP Before Interceptor Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class AopBeforeInterceptorDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader,
                new Class[]{EchoService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                            long startTime = System.currentTimeMillis(); // before
                            try {
                                EchoService echoService = new DefaultEchoService();
                                return echoService.echo((String) args[0]); // target object execute
                            } finally {// after
                                long costTime = System.currentTimeMillis() - startTime;
                                System.out.println("echo method execute cost: " + costTime + "ms");
                            }
                        }
                        return null;
                    }
                }
        );

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");
    }

    interface EchoService {

        String echo(String message);
    }

    static class DefaultEchoService implements EchoService {

        @Override
        public String echo(String message) {
            return "[ECHO] " + message;
        }
    }
}
