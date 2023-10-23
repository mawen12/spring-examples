package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/23
 */
@Configuration
public class SystemPropertyDemo {

	static {
		System.setProperty("user.region", "cn");
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SystemPropertyDemo.class);

		Person person = context.getBean("person", Person.class);

		System.out.println(person.getDefaultValue());

		context.stop();
	}


	@Bean
	public Person person() {
		return new Person();
	}

	@Component
	public static class Person {

		@Value("#{systemProperties['user.region']}") // method 1
		private String defaultValue;

		public String getDefaultValue() {
			return defaultValue;
		}

		@Value("#{systemProperties['user.region']}") // method 2
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
	}
}
