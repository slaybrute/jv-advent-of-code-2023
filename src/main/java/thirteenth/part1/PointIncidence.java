package thirteenth.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;
;
import java.util.ArrayList;
import java.util.List;

public class PointIncidence {
    private String fileName;
    private static final FileReader FILE_READER;
    protected List<List<String>> patterns;

    static {
        FILE_READER = new FileReaderImpl();
    }

    {
        patterns = new ArrayList<>();
    }

    public PointIncidence(String fileName) {
        this.fileName = fileName;
        setPatterns();
    }

    public void setPatterns() {
        List<String> data = FILE_READER.read(fileName);
        List<String> pattern = new ArrayList<>();
        for (String s : data) {
            if (s.isEmpty()) {
                patterns.add(pattern);
                pattern = new ArrayList<>();
                continue;
            }
            pattern.add(s);
        }
        patterns.add(pattern);
    }

    public int getSumOfHorizontalReflections(List<String> pattern) {
        for (int i = 0; i < pattern.size() - 1; i++) {
            if (pattern.get(i).equals(pattern.get(i + 1))) {
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder1 = new StringBuilder();
                for (int q = 1; i - q >= 0 && i + 1 + q < pattern.size(); q++) {
                    stringBuilder.append(pattern.get(i - q));
                    stringBuilder1.append(pattern.get(i + 1 + q));
                }
                if (stringBuilder.toString().contentEquals(stringBuilder1)) {
                    return (i + 1);
                }
            }
        }
        return 0;
    }

    public int getSumOfVerticalReflections(List<String> pattern) {
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < pattern.get(0).length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : pattern) {
                stringBuilder.append(s.charAt(i));
            }
            tmp.add(stringBuilder.toString());
        }
        return getSumOfHorizontalReflections(tmp);
    }

    public int getSumOfAllReflections() {
        int sum = 0;
        for (List<String> pattern : patterns) {
            sum += getSumOfHorizontalReflections(pattern) * 100 + getSumOfVerticalReflections(pattern);
        }
        return sum;
    }
}
