package com.mawen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/17
 */
public class SetValue {

	public static void main(String[] args) {
		Simple simple = new Simple();
		simple.booleanList.add(true);

		SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

		ExpressionParser parser = new SpelExpressionParser();

		parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

		Boolean b = simple.booleanList.get(0);
		System.out.println(b);
	}

	public static class Simple {
		public List<Boolean> booleanList = new ArrayList<>();

	}
}
