package com.mawen;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/16
 */
public class StringConcat {

	public static void main(StringDemo[] args) {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello World'.concat('!')");
		StringDemo message = expression.getValue(StringDemo.class);
		System.out.println(message);
	}
}
