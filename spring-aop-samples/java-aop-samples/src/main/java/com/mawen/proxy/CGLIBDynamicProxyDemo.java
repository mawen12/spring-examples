package com.mawen.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import org.apache.tools.ant.taskdefs.Echo;

import java.awt.event.FocusEvent;

/**
 * CGLIB Dynamic Proxy Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class CGLIBDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{EchoService.class});
        enhancer.setCallback((MethodInterceptor) (obj, method, argss, proxy) -> {
            if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                EchoService echoService = new DefaultEchoService();
                return echoService.echo((String) argss[0]);
            }
            return null;
        });
        Object proxy = enhancer.create();
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
}
