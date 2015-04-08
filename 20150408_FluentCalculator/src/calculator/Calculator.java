package calculator;

/**
 * Kata auf http://www.planetgeek.ch/2013/05/14/the-fluent-calculator-kata/
 *
 */
public class Calculator {
	

	public static FluentResult calc(int initialValue) {
		return new FluentResult(initialValue);
	}


}
