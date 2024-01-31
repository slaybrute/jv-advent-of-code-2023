package workwithfile;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public interface FileReader {
    List<String> read(String fileName);
}
