package thirteenth.part2;

import thirteenth.part1.PointIncidence;

import java.util.ArrayList;
import java.util.List;

public class PointIncidence2 extends PointIncidence {
    public PointIncidence2(String fileName) {
        super(fileName);
    }

    @Override
    public int getSumOfHorizontalReflections(List<String> pattern) {
        for (int i = 0; i < pattern.size() - 1; i++) {
            if (pattern.get(i).equals(pattern.get(i + 1))) {
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder1 = new StringBuilder();
                for (int q = 1; i - q >= 0 && i + 1 + q < pattern.size(); q++) {
                    stringBuilder.append(pattern.get(i - q));
                    stringBuilder1.append(pattern.get(i + 1 + q));
                }
                if (!stringBuilder.toString().contentEquals(stringBuilder1)) {
                    int counter = 0;
                    for (int q = 0; q < stringBuilder.length(); q++) {
                        if (stringBuilder.charAt(q) != stringBuilder1.charAt(q)) {
                            counter++;
                        }
                    }
                    if (counter == 1) {
                        return (i + 1);
                    }
                }
            } else {
                int counter = 0;
                for (int c = 0; c < pattern.get(i).length(); c++) {
                    if (pattern.get(i).charAt(c) != pattern.get(i + 1).charAt(c)) {
                        counter++;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder1 = new StringBuilder();
                for (int q = 1; i - q >= 0 && i + 1 + q < pattern.size(); q++) {
                    stringBuilder.append(pattern.get(i - q));
                    stringBuilder1.append(pattern.get(i + 1 + q));
                }
                if (counter == 1 && stringBuilder1.toString().contentEquals(stringBuilder)) {
                    return (i + 1);
                }
            }
        }
        return 0;
    }
    @Override
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

    @Override
    public int getSumOfAllReflections() {
        int sum = 0;
        for (List<String> pattern : patterns) {
            sum += getSumOfHorizontalReflections(pattern) * 100 + getSumOfVerticalReflections(pattern);
        }
        return sum;
    }
}
