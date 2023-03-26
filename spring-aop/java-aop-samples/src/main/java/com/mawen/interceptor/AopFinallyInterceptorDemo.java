package com.mawen.interceptor;

import org.apache.tools.ant.util.ClasspathUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP Finally Interceptor Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class AopFinallyInterceptorDemo {

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
                            Long startTime = 0L;
                            Long endTime = 0L;
                            Object result = null;
                            try {
                                startTime = (Long) beforeInterceptor.before(proxy, method, args);

                                EchoService echoService = new DefaultEchoService();
                                result = echoService.echo((String) args[0]);

                                AfterInterceptor afterInterceptor = new AfterInterceptor() {
                                    @Override
                                    public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
                                        return System.currentTimeMillis();
                                    }
                                };
                                endTime = (Long) afterInterceptor.after(proxy, method, args, result);

                                return result;
                            } finally {
                                FinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
                                Long costTime = (Long) finallyInterceptor.finalize(proxy, method, args, result);
                                System.out.println("echo method execute cost: " + costTime + "ms");
                            }
                        }
                        return null;
                    }
                }
        );

        EchoService echoService = (EchoService) proxy;
        System.out.println(echoService.echo("Hello,World"));
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

    interface FinallyInterceptor {

        /**
         * finally advice
         *
         * @param proxy
         * @param method
         * @param args
         * @param returnResult
         * @return
         */
        Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
    }

    static class TimeFinallyInterceptor implements FinallyInterceptor {

        private final Long startTime;

        private final Long endTime;

        public TimeFinallyInterceptor(Long startTime, Long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
            return endTime - startTime;
        }
    }
}
