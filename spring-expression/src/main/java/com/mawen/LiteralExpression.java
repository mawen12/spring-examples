package com.mawen;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/23
 */
public class LiteralExpression {

	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();

		String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
		System.out.println(helloWorld);

		double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
		System.out.println(avogadrosNumber);

		int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
		System.out.println(maxValue);

		boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
		System.out.println(trueValue);

		Object nullValue = parser.parseExpression("null").getValue();
		System.out.println(nullValue);
	}

}
