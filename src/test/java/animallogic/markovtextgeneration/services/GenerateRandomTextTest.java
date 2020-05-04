package animallogic.markovtextgeneration.services;

import animallogic.markovtextgeneration.exceptions.InvalidInputException;
import animallogic.markovtextgeneration.model.SuffixPrefix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateRandomTextTest {

    public GenerateRandomText generateRandomText;

    @Before
    public void setup() {

        generateRandomText = new GenerateRandomText();
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowExceptionWhenOutPutLengthIsMoreThanNumberOfWords() {
        SuffixPrefix suffixPrefix = createTestData();
        generateRandomText.generateRandomText(suffixPrefix, 10);
    }

    @Test
    public void outputShouldBeEqualToOutputLength() {
        SuffixPrefix suffixPrefix = createTestData();
        Assert.assertEquals(generateRandomText.generateRandomText(suffixPrefix, 2).split(" ").length, 2);
    }

    @Test
    public void outputShouldBeEqualToOutputLength_wordLength2() {
        SuffixPrefix suffixPrefix = createTestDataSplitByTwo();
        Assert.assertEquals(generateRandomText.generateRandomText(suffixPrefix, 2).split(" ").length, 2);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowExceptionWhenOutPutLengthIsMoreThanNumberOfWords_wordLength2() {
        SuffixPrefix suffixPrefix = createTestDataSplitByTwo();
        generateRandomText.generateRandomText(suffixPrefix, 10);
    }

    public SuffixPrefix createTestData() {
        SuffixPrefix suffixPrefix = new SuffixPrefix();
        Map<String, List<String>> suffixPrefixMap = new HashMap<>();
        suffixPrefixMap.put("one", List.of("fish"));
        suffixPrefixMap.put("fish", List.of("two", "red", "blue", ""));
        suffixPrefixMap.put("two", List.of("fish"));
        suffixPrefixMap.put("red", List.of("fish"));
        suffixPrefixMap.put("blue", List.of("fish"));
        suffixPrefix.setSuffixPrefixMap(suffixPrefixMap);
        suffixPrefix.setNumberOfWords(8);
        return suffixPrefix;
    }

    public SuffixPrefix createTestDataSplitByTwo() {
        SuffixPrefix suffixPrefix = new SuffixPrefix();
        Map<String, List<String>> suffixPrefixMap = new HashMap<>();
        suffixPrefixMap.put("one fish", List.of("two"));
        suffixPrefixMap.put("fish two", List.of("fish"));
        suffixPrefixMap.put("two fish", List.of("red"));
        suffixPrefixMap.put("fish red", List.of("fish"));
        suffixPrefixMap.put("red fish", List.of("blue"));
        suffixPrefixMap.put("fish blue", List.of("fish"));
        suffixPrefixMap.put("blue fish", List.of(""));
        suffixPrefix.setSuffixPrefixMap(suffixPrefixMap);
        suffixPrefix.setNumberOfWords(8);
        return suffixPrefix;
    }
}
