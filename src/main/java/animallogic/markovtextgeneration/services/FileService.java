package animallogic.markovtextgeneration.services;

import animallogic.markovtextgeneration.FileUtils;
import animallogic.markovtextgeneration.model.SuffixPrefix;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService implements FileUtils {

    public void writeToFile(SuffixPrefix suffixPrefix) throws IOException {

        try (RandomAccessFile writer = new RandomAccessFile("suffixPrefixMapFile.txt", "rw");
             FileChannel channel = writer.getChannel();
             FileLock lock = channel.lock()) {
            ByteBuffer buff = ByteBuffer.wrap(SerializationUtils.serialize(suffixPrefix));
            channel.write(buff);
        }
    }

    public SuffixPrefix readFromFile() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("suffixPrefixMapFile.txt"));
        return SerializationUtils.deserialize(data);
    }

}
