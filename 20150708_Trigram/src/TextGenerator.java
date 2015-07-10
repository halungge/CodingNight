import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TextGenerator {
	private final Map<String, List<String>> trigrams = new HashMap<String, List<String>>();
	private final Random random;

	public static void main(String[] args) {
		final TextGenerator textGenerator = new TextGenerator(new Random());
		textGenerator.parseText(args[0]);
		System.out.println(textGenerator.generateText("it", "was"));
	}

	private void parseText(String string) {
		final String[] split = string.split(" ");
		parse(Arrays.asList(split));
	}

	public TextGenerator(Random random) {
		this.random = random;
	}

	public void parse(String firstWord, String secondWord, String followingWord) {
		final String key = computeKey(firstWord, secondWord);
		if (isKeyMissing(key)) {
			trigrams.put(key, new ArrayList<String>());
		}
		trigrams.get(key).add(followingWord);
	}

	private boolean isKeyMissing(String key) {
		return !trigrams.containsKey(key);
	}

	public String generate(String firstWord, String secondWord) {
		final String key = computeKey(firstWord, secondWord);
		if (isKeyMissing(key)) {
			return null;
		}

		final List<String> list = trigrams.get(key);
		return list.get(random.nextInt(list.size()));
	}

	private String computeKey(String firstWord, String secondWord) {
		return firstWord + secondWord;
	}

	public void parse(List<String> asList) {
		for (int i = 2; i < asList.size(); i += 1) {
			parse(asList.get(i - 2), asList.get(i - 1), asList.get(i));
		}
	}

	public String generateText(String firstWord, String secondWord) {
		final StringBuilder sb = new StringBuilder().append(firstWord)
				.append(' ').append(secondWord);
		String next;
		while ((next = generate(firstWord, secondWord)) != null) {
			firstWord = secondWord;
			secondWord = next;
			sb.append(' ').append(next);
		}

		return sb.toString();
	}

}
