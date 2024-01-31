package third.part2;

import third.part1.GearRatios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GearRatios2 extends GearRatios {
    private final Map<List<Integer>, List<Integer>> coincidedGears;

    {
        coincidedGears = new HashMap<>();
    }

    public GearRatios2(String fileName) {
        super(fileName);
    }

    public Map<List<Integer>, List<Integer>> findCoincidedGears() {
        // {coordinate, numbers}
        super.fillParsedGear();
        StringBuilder numberBuilder;
        for (int i = 0; i < parsedGear.length; i++) {
            for (int q = 0; q < parsedGear[0].length; q++) {
                if (Character.isDigit(parsedGear[i][q])) {
                    int y = q;
                    while (Character.isDigit(parsedGear[i][y]) && y < parsedGear[0].length - 1) {
                        y++;
                    }
                    numberBuilder = new StringBuilder();
                    if (i != 0) {
                        if (q != 0) { //перемога
                            if (parsedGear[i][q - 1] == '*') {
                                q = appendNumberAndGetQ(i, q, i, q - 1, numberBuilder);
                                continue;
                            }
                        }
                        if (y - 1 < parsedGear[0].length - 1) { // перемога
                            if (parsedGear[i][y] == '*') {
                                q = appendNumberAndGetQ(i, q, i, y, numberBuilder);
                                continue;
                            }
                        }
                        if (q == 0) {
                            for (int t = q; t < q + y + 1 && t < parsedGear[0].length; t++) {
                                if (parsedGear[i - 1][t] == '*') {
                                    q = appendNumberAndGetQ(i, q, i - 1, t, numberBuilder);
                                    break;
                                }
                            }
                        } else {
                            for (int t = q - 1; t < y + 1 && t < parsedGear[0].length; t++) {
                                if (parsedGear[i - 1][t] == '*') {
                                    q = appendNumberAndGetQ(i, q, i - 1, t, numberBuilder);
                                    break;
                                }
                            }
                        }
                        if (!numberBuilder.isEmpty()) {
                            continue;
                        }
                    }
                    //-------------------------------------------------- if(i != 0) second part verifying
                    if (i != parsedGear.length - 1) {
                        if (q != 0) {
                            if (parsedGear[i][q - 1] == '*') {
                                int o = q - 1;
                                q = appendNumberAndGetQ(i, q, i + 1, q - 1, numberBuilder);
                                continue;
                            }
                        }
                        if (y - 1 < parsedGear[0].length - 1) {
                            if (parsedGear[i][y] == '*') {
                                q = appendNumberAndGetQ(i, q, i + 1, y, numberBuilder);
                                continue;
                            }
                        }
                        if (q == 0) {
                            for (int t = q; t < q + y + 1 && t < parsedGear[0].length; t++) {
                                if (parsedGear[i + 1][t] == '*') {
                                    q = appendNumberAndGetQ(i, q, i + 1, t, numberBuilder);
                                    break;
                                }
                            }
                        } else {
                            for (int t = q - 1; t < y + 1 && t < parsedGear[0].length; t++) {
                                if (parsedGear[i + 1][t] == '*') {
                                    q = appendNumberAndGetQ(i, q, i + 1, t, numberBuilder);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return coincidedGears;
    }

    private void fillCoincidedGears(List<Integer> coordinates, int number) {
        if (coincidedGears.containsKey(coordinates)) {
            coincidedGears.get(coordinates).add(number);
        } else {
            coincidedGears.put(coordinates, new ArrayList<>(List.of(number)));
        }
    }

    @Override
    public int getSumOfAllGearNumbers() {
        findCoincidedGears();
        int sum = 0;
        for (List<Integer> values : coincidedGears.values()) {
            if (values.size() == 2) {
                sum += values.get(0) * values.get(1);
            }
        }
        return sum;
    }

    protected int appendNumberAndGetQ(int i, int q, int x, int y, StringBuilder numberBuilder) {
        numberBuilder.append(parsedGear[i][q]);
        if (Character.isDigit(parsedGear[i][q + 1])) {
            numberBuilder.append(parsedGear[i][++q]);
            if (Character.isDigit(parsedGear[i][q + 1])) {
                numberBuilder.append(parsedGear[i][++q]);
            }
        }
        fillCoincidedGears(new ArrayList<>(List.of(x, y)), Integer.parseInt(numberBuilder.toString()));
        q++;
        return q;
    }
}
