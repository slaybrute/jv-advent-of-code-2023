package second.part2;

import second.part1.CubeConundrum;

public class CubeConundrum2 extends CubeConundrum {
    private int red;
    private int blue;
    private int green;

    public CubeConundrum2(String fileName) {
        super(fileName);
    }

    public int getSumOfPowersMinSet() {
        int sum = 0;
        for (String game : super.data) {
            sum += getPowerOfMinSetInGame(game);
        }
        return sum;
    }

    private int getPowerOfMinSetInGame(String game) {
        red = 0;
        blue = 0;
        green = 0;
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
                updateResult(color, number);
                numberBuilder = new StringBuilder();
            }
        }
        return red * blue * green;
    }

    private void updateResult(char color, int number) {
        switch (color) {
            case 'r' -> {
                if (red < number) {
                    red = number;
                }
            }
            case 'g' -> {
                if (green < number) {
                    green = number;
                }
            }
            case 'b' -> {
                if (blue < number) {
                    blue = number;
                }
            }
        }
    }
}
