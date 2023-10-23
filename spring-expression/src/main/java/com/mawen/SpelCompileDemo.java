package com.mawen;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/17
 */
public class SpelCompileDemo {

	public static void main(String[] args) {
		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, SpelCompileDemo.class.getClassLoader());

		SpelExpressionParser parser = new SpelExpressionParser(config);

		Expression expression = parser.parseExpression("payload");

		MyMessage message = new MyMessage("Hello");

		System.out.println(expression.getValue(message, String.class));
	}

	public static class MyMessage {
		public String payload;

		public MyMessage(String payload) {
			this.payload = payload;
		}
	}
}
