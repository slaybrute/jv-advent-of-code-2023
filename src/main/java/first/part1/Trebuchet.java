package first.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.List;

public class Trebuchet {
    private static final FileReader FILE_READER;
    private String fileName;
    private List<String> data;

    static {
        FILE_READER = new FileReaderImpl();
    }

    public Trebuchet(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        data = FILE_READER.read(fileName);
    }

    public int getSumOfCalibratedNumbers() {
        int sum = 0;
        for (String number : data) {
            sum += getDecryptedNumber(number);
        }
        return sum;
    }

    public int getDecryptedNumber(String encryptedNumber) {
        StringBuilder numberBuilder = new StringBuilder();
        char[] parsedString = encryptedNumber.toCharArray();
        for (char c : parsedString) {
            if (c >= 48 && c <= 57) {
                numberBuilder.append(c);
                break;
            }
        }
        for (int i = parsedString.length - 1; i >= 0; i--) {
            if (parsedString[i] >= 48 && parsedString[i] <= 57) {
                numberBuilder.append(parsedString[i]);
                break;
            }
        }
        return Integer.parseInt(numberBuilder.toString());
    }
}
