package workwithfile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> read(String fileName) {
        try {
            Path filePath = Paths.get(FileReaderImpl.class.getClassLoader().getResource(fileName).toURI());
            return Files.readAllLines(filePath);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Cannot read data from file: " + fileName, e);
        }
    }
}
