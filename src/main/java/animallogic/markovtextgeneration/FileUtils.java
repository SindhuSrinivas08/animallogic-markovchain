package animallogic.markovtextgeneration;

import animallogic.markovtextgeneration.model.SuffixPrefix;

import java.io.IOException;


public interface FileUtils {

    void writeToFile(SuffixPrefix suffixPrefix) throws IOException;

    SuffixPrefix readFromFile() throws IOException;
}
