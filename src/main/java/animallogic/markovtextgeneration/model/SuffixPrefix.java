package animallogic.markovtextgeneration.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonAutoDetect
public class SuffixPrefix implements Serializable {

    private Map<String, List<String>> suffixPrefixMap;

    private int numberOfWords;

    private int wordLength;

    public Map<String, List<String>> getSuffixPrefixMap() {
        return suffixPrefixMap;
    }

    public void setSuffixPrefixMap(Map<String, List<String>> suffixPrefixMap) {
        this.suffixPrefixMap = suffixPrefixMap;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }
}
