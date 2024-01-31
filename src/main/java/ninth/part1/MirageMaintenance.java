package ninth.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.ArrayList;
import java.util.List;

public class MirageMaintenance {
    private static final FileReader FILE_READER;
    private String fileName;
    private List<String> data;

    static {
        FILE_READER = new FileReaderImpl();
    }

    public MirageMaintenance(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        data = FILE_READER.read(fileName);
    }

    public int getNextValue(List<Integer> history) {
        List<Integer> lastValues = new ArrayList<>();
        while (!isAllValuesZero(history)) {
            lastValues.add(history.get(history.size() - 1));
            List<Integer> tmp = new ArrayList<>();
            for (int i = 1; i < history.size(); i++) {
                tmp.add(history.get(i) - history.get(i - 1));
            }
            history = tmp;
        }
        return lastValues.stream().mapToInt(Integer::valueOf).sum();
    }

    protected boolean isAllValuesZero(List<Integer> list) {
        int counter = 0;
        for (int number : list) {
            if (number == 0) {
                counter++;
            }
        }
        return counter == list.size();
    }

    public int getSumOfAllRows() {
        int sum = 0;
        for (String s : data) {
            List<Integer> tmp = new ArrayList<>();
            for (String s1 : s.split(" ")) {
                tmp.add(Integer.parseInt(s1));
            }
            sum += getNextValue(tmp);
        }
        return sum;
    }
}
