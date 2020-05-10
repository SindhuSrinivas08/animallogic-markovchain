package animallogic.markovtextgeneration.services;

import animallogic.markovtextgeneration.exceptions.InvalidInputException;
import animallogic.markovtextgeneration.model.SuffixPrefix;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenerateRandomText {

    public String generateRandomText(SuffixPrefix suffixPrefix, int outputLength) {
        if (suffixPrefix.getNumberOfWords() < outputLength) {
            throw new InvalidInputException("output length cannot be greater than number of words");
        }

        Map<String, List<String>> suffixPrefixes = suffixPrefix.getSuffixPrefixMap();
        Random random = new Random();
        String prefix = (String) suffixPrefixes.keySet().toArray()[random.nextInt(suffixPrefixes.size())];
        List<String> output = new ArrayList<>(Arrays.asList(prefix.split(" ")));

        for (int i = 0; output.size() < outputLength; i++) {
            List<String> lst = suffixPrefixes.get(prefix);
            if (lst != null) {
                output.add(lst.get(random.nextInt(lst.size())));
                prefix = output.subList(i + 1, output.size()).stream().reduce("", (a, b) -> a + " " + b).trim();
            } else {
                break;
            }

        }
        return output.stream().reduce("", (a, b) -> a + " " + b).trim();
    }
}
