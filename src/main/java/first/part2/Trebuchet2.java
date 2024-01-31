package first.part2;

import java.util.HashMap;

import first.part1.Trebuchet;

import java.util.Map;

public class Trebuchet2 extends Trebuchet {
    private static final Map<String, Integer> NUMBER_NAMES;

    static {
        NUMBER_NAMES = new HashMap<>() {{
            put("zero", 0);
            put("one", 1);
            put("two", 2);
            put("three", 3);
            put("four", 4);
            put("five", 5);
            put("six", 6);
            put("seven", 7);
            put("eight", 8);
            put("nine", 9);
        }};

    }

    public Trebuchet2(String fileName) {
        super(fileName);
    }

    @Override
    public int getDecryptedNumber(String encryptedNumber) {
        Map<Integer, Integer> map = new HashMap<>();
        char[] parsedString = encryptedNumber.toCharArray();
        for (int i = 0; i < parsedString.length; i++) {
            if (parsedString[i] >= 48 && parsedString[i] <= 57) {
                map.put(i, Integer.parseInt(String.valueOf(parsedString[i])));
            }
        }
        StringBuilder stringBuilder = new StringBuilder(encryptedNumber);
        for (int i = 0; i < 2; i++) {
            for (String name : NUMBER_NAMES.keySet()) {
                if (stringBuilder.toString().contains(name)) {
                    if (i == 0) {
                        map.put(encryptedNumber.indexOf(name), NUMBER_NAMES.get(name));
                    } else {
                        stringBuilder.delete(stringBuilder.indexOf(name), stringBuilder.indexOf(name) + name.length());
                        map.put(encryptedNumber.lastIndexOf(name), NUMBER_NAMES.get(name));
                    }

                }
            }
        }
        StringBuilder numberBuilder = new StringBuilder();
        numberBuilder.append(map.get(map.keySet().stream().min(Integer::compareTo).get()))
                .append(map.get(map.keySet().stream().max(Integer::compareTo).get()));
        return Integer.parseInt(numberBuilder.toString());
    }
}
