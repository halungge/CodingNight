package calculator;

import java.util.ArrayList;
import java.util.List;

public class FluentResult {

	private final List<Integer> values = new ArrayList<Integer>();
	private int nextIndex;

	public FluentResult(int initialValue) {
		addValue(initialValue);
	}

	public int result() {
		return values.get(currentIndex());
	}

	public FluentResult plus(int increment) {
		addValue(result() + increment);
		return this;
	}

	public FluentResult minus(int decrement) {
		addValue(result() - decrement);
		return this;
	}

	public FluentResult undo() {
		if (currentIndex() > 0) {
			nextIndex--;
		}
		return this;
	}

	public FluentResult redo() {
		if (currentIndex() < values.size() - 1) {
			nextIndex++;
		}
		return this;
	}

	private int currentIndex() {
		return nextIndex - 1;
	}

	private void addValue(int value) {
		final List<Integer> subList = new ArrayList<Integer>(values.subList(0, nextIndex));
		values.clear();
		values.addAll(subList);
		values.add(value);
		
		nextIndex++;
	}

	public FluentResult save() {
		final int value = result();
		values.clear();
		values.add(value);
		return this;
	}
}
