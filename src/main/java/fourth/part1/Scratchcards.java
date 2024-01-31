package fourth.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.List;

public class Scratchcards {
    private static final FileReader FILE_READER;
    private String fileName;
    protected List<String> data;

    static {
        FILE_READER = new FileReaderImpl();
    }

    public Scratchcards(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        data = FILE_READER.read(fileName);
    }

    public int getSumOfMatchedNumbers() {
        int sum = 0;
        for (String s : data) {
            String[] part1 = s.split("\\|")[0].split(":")[1].split(" ");
            String[] part2 = s.split("\\|")[1].split(" ");
            sum += getAmountOfMatches(part1, part2);
        }
        return sum;
    }

    protected int getAmountOfMatches(String[] part1, String[] part2) {
        int amount = 0;
        boolean isFirst = true;
        for (String p2 : part2) {
            for (String p1 : part1) {
                if (!p1.isEmpty() && p1.equals(p2)) {
                    if (isFirst) {
                        amount++;
                        isFirst = false;
                    } else {
                        amount *= 2;
                    }
                }
            }
        }
        return amount;
    }
}