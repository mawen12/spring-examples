package com.mawen.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP After Interceptor Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class AopAfterInterceptorDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader,
                new Class[]{EchoService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                            BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                                @Override
                                public Object before(Object proxy, Method method, Object[] args) {
                                    return System.currentTimeMillis();
                                }
                            };
                            Long startTime = (Long) beforeInterceptor.before(proxy, method, args);

                            Object result = null;
                            EchoService echoService = new DefaultEchoService();
                            result = echoService.echo((String) args[0]);

                            AfterInterceptor afterInterceptor = new AfterInterceptor() { // after
                                @Override
                                public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
                                    return System.currentTimeMillis();
                                }
                            };
                            Long endTime = (Long) afterInterceptor.after(proxy, method, args, result);

                            return result;
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

    /**
     * After Interceptor
     */
    interface AfterInterceptor {

        /**
         * After Advice
         *
         * @param proxy
         * @param method
         * @param args
         * @param returnResult
         * @return
         */
        Object after(Object proxy, Method method, Object[] args, Object returnResult);
    }
}
