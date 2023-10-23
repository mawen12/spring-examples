package com.mawen;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/16
 */
public class StringProperty {
	public static void main(String[] args) {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello World'.bytes.length");
		Integer value = expression.getValue(Integer.class);
		System.out.println(value);
	}
}
