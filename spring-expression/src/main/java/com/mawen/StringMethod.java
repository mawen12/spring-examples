package com.mawen;

import java.util.Arrays;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/16
 */
public class StringMethod {

	public static void main(String[] args) {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello World'.bytes");
		Byte[] message = expression.getValue(Byte[].class);
		System.out.println(Arrays.toString(message));

		expression = parser.parseExpression("'Hello World'.bytes.length");
		Integer length = expression.getValue(Integer.TYPE);
		System.out.println(length);

		expression = parser.parseExpression("new String('Hello World').toUpperCase()");
		String content = expression.getValue(String.class);
		System.out.println(content);
	}

}
