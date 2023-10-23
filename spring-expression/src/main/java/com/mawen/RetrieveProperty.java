package com.mawen;

import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/10/17
 */
public class RetrieveProperty {

	public static void main(String[] args) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("name");
		String name = expression.getValue(tesla, String.class);
		System.out.println(name);

		expression = parser.parseExpression("name == 'Nikola Tesla'");
		Boolean result = expression.getValue(tesla, Boolean.class);
		System.out.println(result);
	}

	public static class Inventor {
		private String name;

		private Date birthday;

		private String notionality;

		public Inventor(String name, Date birthday, String notionality) {
			this.name = name;
			this.birthday = birthday;
			this.notionality = notionality;
		}

		public String getName() {
			return name;
		}

		public Date getBirthday() {
			return birthday;
		}

		public String getNotionality() {
			return notionality;
		}
	}

}
