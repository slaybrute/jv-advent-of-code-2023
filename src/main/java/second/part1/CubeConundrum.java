package second.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.List;

public class CubeConundrum {
    private static final int MAX_RED_AMOUNT = 12;
    private static final int MAX_GREEN_AMOUNT = 13;
    private static final int MAX_BLUE_AMOUNT = 14;
    private static final FileReader FILE_READER;
    private String fileName;
    protected List<String> data;

    static {
        FILE_READER = new FileReaderImpl();
    }

    public CubeConundrum(String fileName) {
        this.fileName = fileName;
        setData();
    }
    public void setData() {
        data = FILE_READER.read(fileName);
    }

    public int getSumOfPossibleGames() {
        int sum = 0;
        for (String game : data) {
            StringBuilder numberBuilder = new StringBuilder();
            if (isGamePossible(game)) {

                numberBuilder.append(game.charAt(5));
                if (Character.isDigit(game.charAt(6))) {
                    numberBuilder.append(game.charAt(6));
                }
                sum += Integer.parseInt(numberBuilder.toString());
            }
        }
        return sum;
    }

    private boolean isGamePossible(String game) {
        char[] parsedGame = game.split(":")[1].toCharArray();
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < parsedGame.length; i++) {
            if (Character.isDigit(parsedGame[i])) {
                for (int q = i; q < parsedGame.length
                        && Character.isDigit(parsedGame[q]); q++, i++) {
                    numberBuilder.append(parsedGame[q]);

                }
                int number = Integer.parseInt(numberBuilder.toString());
                char color = parsedGame[i + 1];
                if (!isResultPositive(color, number)) {
                    return false;
                }
                numberBuilder = new StringBuilder();
            }
        }
        return true;
    }

    private boolean isResultPositive(char color, int number) {
        switch (color) {
            case 'r' -> {
                if (number > MAX_RED_AMOUNT) {
                    return false;
                }
            }
            case 'g' -> {
                if (number > MAX_GREEN_AMOUNT) {
                    return false;
                }
            }
            case 'b' -> {
                if (number > MAX_BLUE_AMOUNT) {
                    return false;
                }
            }
        }
        return true;
    }
}
