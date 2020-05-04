package animallogic.markovtextgeneration.services;

import animallogic.markovtextgeneration.exceptions.InvalidInputException;
import animallogic.markovtextgeneration.model.SuffixPrefix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class GenerateSuffixPrefixTest {

    public GenerateSuffixPrefix generateSuffixPrefix;

    @Before
    public void setup() {
        generateSuffixPrefix = new GenerateSuffixPrefix();
    }

    @Test(expected = InvalidInputException.class)
    public void shouldFailWhenContentsAreEmpty() {
        generateSuffixPrefix.validateContents("     ", 3);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldFailWhenWordLengthIsGreaterThanText() {
        String fileContents = "Today you are you. That is truer than true. There is no one alive who is you-er than you.";
        generateSuffixPrefix.validateContents(fileContents, 20);
    }

    @Test
    public void shouldPassWithStringArray() {
        String fileContents = "Today you are you. That is truer than true. There is no one alive who is you-er than you.";
        Assert.assertEquals(generateSuffixPrefix.validateContents(fileContents, 2).length, 19);
    }

    @Test
    public void verifySuffixPrefixMapKeyLength_splitBy1() {
        String fileContents = "Today you are you. That is truer than true. There is no one alive who is you-er than you.";
        SuffixPrefix suffixPrefix = generateSuffixPrefix.generateSuffixPrefix(fileContents, 1);
        Assert.assertEquals(suffixPrefix.getSuffixPrefixMap().size(), 15);
        assertThat(suffixPrefix.getSuffixPrefixMap().get("is"), containsInAnyOrder(List.of("truer", "no", "you-er").toArray()));
        assertThat(suffixPrefix.getSuffixPrefixMap().get("you."), containsInAnyOrder(List.of("that", "").toArray()));
    }

    @Test
    public void verifySuffixPrefixMapKeyLength_splitBy2() {
        String fileContents = "You have brains in your head. You have feet in your shoes. You can steer yourself any direction you choose. You’re on your own.";
        SuffixPrefix suffixPrefix = generateSuffixPrefix.generateSuffixPrefix(fileContents, 2);
        Assert.assertEquals(suffixPrefix.getSuffixPrefixMap().size(), 21);
        assertThat(suffixPrefix.getSuffixPrefixMap().get("in your"), containsInAnyOrder(List.of("shoes.", "head.").toArray()));
        assertThat(suffixPrefix.getSuffixPrefixMap().get("you have"), containsInAnyOrder(List.of("brains", "feet").toArray()));
    }

}
