import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TrigramTest {
	private static final Random MOCK2 = mock(Random.class);
	private final TextGenerator textGenerator = new TextGenerator(MOCK2);

	@Test
	public void unknown() throws Exception {
		assertNull(textGenerator.generate("the", "quick"));
	}
	
	@Test
	public void findSeveral() throws Exception {
		textGenerator.parse("the", "quick", "brown");
		textGenerator.parse("the", "slow", "black");
		assertEquals("brown", textGenerator.generate("the", "quick"));
		assertEquals("black", textGenerator.generate("the", "slow"));
	}
	
	@Test
	public void severalWordsForKeyReturnFirst(){
		when(MOCK2.nextInt(2)).thenReturn(0);
		textGenerator.parse("the", "quick", "brown");
		textGenerator.parse("the", "quick", "black");
		assertEquals("brown", textGenerator.generate("the", "quick"));
	}
	
	@Test
	public void parseWordList(){
		final List<String> asList = Arrays.asList(new String[]{"the", "quick", "brown", "fox", "jumps"});
		textGenerator.parse(asList);
		assertEquals("fox", textGenerator.generate("quick", "brown"));
		assertEquals("brown", textGenerator.generate("the", "quick"));
		assertEquals("jumps", textGenerator.generate("brown", "fox"));
		assertNull(textGenerator.generate("fox", "jumps"));
		
	}
		
	@Test
	public void generateText(){
		final List<String> asList = Arrays.asList(new String[]{"the", "quick", "brown", "fox", "jumps"});
		textGenerator.parse(asList);
		assertEquals("quick brown fox jumps", textGenerator.generateText("quick", "brown"));
	}
}
