package com.mawen.proxy;

/**
 * Java Static Proxy Demo
 * <p>
 * -  inherit
 * <p>
 * -  combination
 * <p>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/11
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello,World");
    }


    interface EchoService {
        String echo(String message);
    }

    /**
     * Default {@link EchoService} Implementation
     * - inherit
     */
    static class DefaultEchoService implements EchoService {

        @Override
        public String echo(String message) {
            return "[ECHO]" + message;
        }
    }

    static class ProxyEchoService implements EchoService {
        // combination
        private final EchoService echoService;

        public ProxyEchoService(EchoService echoService) {
            this.echoService = echoService;
        }

        @Override
        public String echo(String message) {
            long startTime = System.currentTimeMillis();
            String result = echoService.echo(message);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.println("echo method execute cost: " + costTime + "ms");
            return result;
        }
    }
}
