package com.mawen.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class AopBeforeInterceptorDemo2 {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Proxy.newProxyInstance(classLoader,
                new Class[]{EchoService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                            // before
                            BeforeInterceptor beforeInterceptor = new BeforeInterceptor(){
                                @Override
                                public Object before(Object proxy, Method method, Object[] args) {
                                    return System.currentTimeMillis();
                                }
                            };

                            Long startTime = (Long) beforeInterceptor.before(proxy, method, args);
                            EchoService echoService = new DefaultEchoService();
                            return echoService.echo((String) args[0]);
                        }
                        return null;
                    }
                });
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

    /**
     * Before Interceptor
     */
    interface BeforeInterceptor {

        /**
         * Before Advice
         *
         * @param proxy
         * @param method
         * @param args
         * @return
         */
        Object before(Object proxy, Method method, Object[] args);
    }
}
