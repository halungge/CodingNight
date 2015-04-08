package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void calcResult() {
		final int result = Calculator.calc(0).result();
		assertEquals(0, result);
	}

	@Test
	public void calcResult10() {
		final int result = Calculator.calc(10).result();
		assertEquals(10, result);
	}

	@Test
	public void undoOnInitialResult() {
		final int result = Calculator.calc(10).undo().result();
		assertEquals(10, result);
	}
	
	@Test
	public void redoOnInitialResult() {
		final int result = Calculator.calc(10).redo().result();
		assertEquals(10, result);
	}
	
	@Test
	public void minus() throws Exception {
		final int result = Calculator.calc(10).minus(2).result();
		assertEquals(8, result);
	}
	
	@Test
	public void plus() throws Exception {
		final int result = Calculator.calc(10).plus(5).result();
		assertEquals(15, result);
	}
	
	@Test
	public void plusUndo() throws Exception {
		final int result = Calculator.calc(10).plus(5).undo().result();
		assertEquals(10, result);
	}
	
	@Test
	public void plusUndoRedo() throws Exception {
		final int result = Calculator.calc(10).plus(5).undo().redo().result();
		assertEquals(15, result);
	}
	
	@Test
	public void minusUndoPlus() throws Exception {
		final int result = Calculator.calc(10).minus(2).undo().plus(5).result();
		assertEquals(15, result);
	}
	
	@Test
	public void plusUndoMinusRedo() throws Exception {
		final int result = Calculator.calc(10).plus(5).undo().minus(2).redo().result();
		assertEquals(8, result);
	}
	
	@Test
	public void save() throws Exception {
		final int result = Calculator.calc(10).plus(5).save().undo().result();
		assertEquals(15, result);
	}
	
}
