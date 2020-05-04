package animallogic.markovtextgeneration.services;

import animallogic.markovtextgeneration.exceptions.InvalidInputException;
import animallogic.markovtextgeneration.model.SuffixPrefix;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenerateSuffixPrefix {

    public String[] validateContents(String inputText, int wordLength) {
        String originalText = inputText.toLowerCase();
        if (inputText.trim().length() == 0) {
            throw new InvalidInputException("file cannot be empty");
        }
        String[] splitText = originalText.split(" ");
        if (splitText.length < wordLength) {
            throw new InvalidInputException("Word length must be less than the number of word in text file");
        }

        return splitText;
    }

    public SuffixPrefix generateSuffixPrefix(String inputText, int wordLength) {
        String[] splitText = validateContents(inputText, wordLength);
        Map<String, List<String>> mp = new HashMap<>();
        SuffixPrefix suffixPrefix = new SuffixPrefix();

        for (int i = 0; i <= splitText.length - wordLength; i++) {
            StringBuilder key = new StringBuilder(splitText[i]);
            for (int l = i + 1; l < wordLength + i; l++) {
                key.append(" ").append(splitText[l]);

            }
            String value = i + wordLength < splitText.length ? splitText[i + wordLength] : "";
            if (mp.containsKey(key.toString())) {
                mp.get(key.toString()).add(value);
            } else {
                List<String> suffixes = new ArrayList<>();
                suffixes.add(value);
                mp.put(key.toString(), suffixes);
            }
        }
        suffixPrefix.setSuffixPrefixMap(mp);
        suffixPrefix.setNumberOfWords(splitText.length);
        suffixPrefix.setWordLength(wordLength);
        return suffixPrefix;
    }
}
