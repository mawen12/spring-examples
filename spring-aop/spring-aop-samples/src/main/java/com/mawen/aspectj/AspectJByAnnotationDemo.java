package com.mawen.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * Spring AOP AspectJ Base {@link AspectJ} Demo
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/12
 */
public class AspectJByAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJConfig.class);
        context.refresh();

        AspectJConfig aspectJConfig = context.getBean(AspectJConfig.class);
        System.out.println(aspectJConfig.getClass().getName()); // AspectJ
        System.out.println(Arrays.toString(aspectJConfig.getClass().getAnnotations()));

        context.close();
    }

    @Aspect // declare aspect facted
    @Configuration // declare configuration class
    @EnableAspectJAutoProxy // enable @AsepctJ
    static class AspectJConfig {

    }

}
