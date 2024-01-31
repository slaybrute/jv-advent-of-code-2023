package third.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;
import java.util.ArrayList;
import java.util.List;

public class GearRatios {
    private final List<Integer> gearNumbers;
    private static final FileReader FILE_READER;
    private String fileName;
    protected char[][] parsedGear;
    protected List<String> data;

    static {
        FILE_READER = new FileReaderImpl();
    }

    {
        gearNumbers = new ArrayList<>();
    }

    public GearRatios(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        data = FILE_READER.read(fileName);
    }

    protected void fillParsedGear() {
        parsedGear = new char[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            char[] parsedGearLine = data.get(i).toCharArray();
            System.arraycopy(parsedGearLine, 0, parsedGear[i], 0, parsedGearLine.length);
        }
    }

    public List<Integer> fillGearNumbers() {
        fillParsedGear();
        StringBuilder numberBuilder;
        for (int i = 0; i < parsedGear.length; i++) {
            for (int q = 0; q < parsedGear[0].length; q++) {
                if (Character.isDigit(parsedGear[i][q])) {
                    int y = q;
                    while (Character.isDigit(parsedGear[i][y]) && y < parsedGear[0].length - 1) {
                        y++;
                    }
                    int prevSize = gearNumbers.size();
                    numberBuilder = new StringBuilder();
                    if (i != 0) {
                        if (q != 0) { //перемога
                            if (parsedGear[i][q - 1] != '.' && !Character.isDigit(parsedGear[i][q - 1])) {
                                q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                continue;
                            }
                        }
                        if (y - 1 < parsedGear[0].length - 1) { // перемога
                            if (parsedGear[i][y] != '.' && !Character.isDigit(parsedGear[i][y])) {
                                q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                continue;
                            }
                        }
                        if (q == 0) {
                            for (int t = q; t < q + y + 1 && t < parsedGear[0].length; t++) {
                                if (!Character.isDigit(parsedGear[i - 1][t]) && parsedGear[i - 1][t] != '.') {
                                    q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                    break;
                                }
                            }
                        } else {
                            for (int t = q - 1; t < y + 1 && t < parsedGear[0].length; t++) {
                                if (!Character.isDigit(parsedGear[i - 1][t]) && parsedGear[i - 1][t] != '.') {
                                    q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                    break;
                                }
                            }
                        }
                        if (prevSize != gearNumbers.size()) {
                            continue;
                        }
                    }
                    //-------------------------------------------------- if(i != 0)
                    if (i != parsedGear.length - 1) {
                        if (q != 0) {
                            if (parsedGear[i][q - 1] != '.' && !Character.isDigit(parsedGear[i][q - 1])) {
                                q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                continue;
                            }
                        }
                        if (y - 1 < parsedGear[0].length - 1) {
                            if (parsedGear[i][y] != '.' && !Character.isDigit(parsedGear[i][y])) {
                                q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                continue;
                            }
                        }
                        if (q == 0) {
                            for (int t = q; t < q + y + 1 && t < parsedGear[0].length; t++) {
                                if (!Character.isDigit(parsedGear[i + 1][t]) && parsedGear[i + 1][t] != '.') {
                                    q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                    break;
                                }
                            }
                        } else {
                            for (int t = q - 1; t < y + 1 && t < parsedGear[0].length; t++) {
                                if (!Character.isDigit(parsedGear[i + 1][t]) && parsedGear[i + 1][t] != '.') {
                                    q = appendNumberAndGetQ(gearNumbers, i, q, numberBuilder);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return gearNumbers;
    }

    public int getSumOfAllGearNumbers() {
        fillGearNumbers();
        int sum = 0;
        for (int number : gearNumbers) {
            sum += number;
        }
        return sum;
    }

    protected int appendNumberAndGetQ(List<Integer> adjacentNumbers, int i, int q, StringBuilder numberBuilder) {
        numberBuilder.append(parsedGear[i][q]);
        if (Character.isDigit(parsedGear[i][q + 1])) {
            numberBuilder.append(parsedGear[i][++q]);
            if (Character.isDigit(parsedGear[i][q + 1])) {
                numberBuilder.append(parsedGear[i][++q]);
            }
        }
        adjacentNumbers.add(Integer.parseInt(numberBuilder.toString()));
        q++;
        return q;
    }
}
