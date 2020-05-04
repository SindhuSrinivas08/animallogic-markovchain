package animallogic.markovtextgeneration.controllers;

import animallogic.markovtextgeneration.FileUtils;
import animallogic.markovtextgeneration.services.GenerateRandomText;
import animallogic.markovtextgeneration.services.GenerateSuffixPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MarkovTextGenerationController {


    @Autowired
    public GenerateSuffixPrefix generateSuffixPrefix;

    @Autowired
    public GenerateRandomText generateRandomText;

    @Autowired
    public FileUtils fileUtils;

    @PostMapping("/upload/file")
    public ResponseEntity uploadTextFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam int wordLength) throws IOException {
        String content = new String(multipartFile.getBytes());
        fileUtils.writeToFile(generateSuffixPrefix.generateSuffixPrefix(content, wordLength));
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/generate/random-text")
    public ResponseEntity generateRandomText(@RequestParam int outputLength) throws IOException {
        return new ResponseEntity(generateRandomText.generateRandomText(fileUtils.readFromFile(), outputLength), HttpStatus.OK);
    }
}
