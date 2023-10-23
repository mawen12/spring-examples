package com.mawen;

import java.util.List;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/17
 */
public class GrowList {

	public static void main(String[] args) {
		SpelParserConfiguration config = new SpelParserConfiguration(true, true);

		SpelExpressionParser parser = new SpelExpressionParser(config);

		Expression expression = parser.parseExpression("list[3]");

		Demo demo = new Demo();

		Object o = expression.getValue(demo);

		System.out.println(o);
	}

	public static class Demo {
		public List<String> list;
	}
}
