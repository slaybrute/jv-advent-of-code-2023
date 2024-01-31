package twelfth.part1;

import twelfth.CombinatorialList;
import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class HotSprings {
    protected static final FileReader FILE_READER;
    protected String fileName;
    protected Map<String, List<Integer>> data;

    static {
        FILE_READER = new FileReaderImpl();
    }

    {
        data = new HashMap<>();
    }

    public HotSprings(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        for (String s : FILE_READER.read(fileName)) {
            String[] secondPart = s.split(" ")[1].split(",");
            List<Integer> tmp = new ArrayList<>();
            for (String number : secondPart) {
                tmp.add(Integer.parseInt(number));
            }
            data.put(s.split(" ")[0], tmp);
        }
    }

    public int getSumOfAll() {
        AtomicInteger sum = new AtomicInteger();
        data.keySet().parallelStream().forEach(key -> {
            sum.addAndGet(getAmountOfCombinations(key, data.get(key)));
        });
        return sum.get();
    }

    public int getAmountOfCombinations(String key, List<Integer> values) {
        int counter = 0;
        char[] parsedKey = key.toCharArray();
        Set<List<Object>> combinations = getAllCombinationsOfSigns(countSigns(parsedKey), values);
        for (List<Object> objects : combinations) {
            for (int i = 0, q = 0; i < parsedKey.length; i++) {
                if (parsedKey[i] == '?') {
                    parsedKey[i] = (char) objects.get(q);
                    q++;
                }
            }
            if (isRowMatchesPattern(parsedKey, values)) {
                counter++;
            }
            parsedKey = key.toCharArray();
        }
        return counter;
    }

    public boolean isRowMatchesPattern(char[] parsedKey, List<Integer> values) {
        int counter;
        int start = 0;
        for (int i = 0; i < parsedKey.length; i++) {
            counter = 0;
            if (parsedKey[i] == '#') {
                counter++;
                for (int q = i + 1; q < parsedKey.length && parsedKey[q] == '#'; q++, i++) {
                    counter++;
                }
                if (!values.get(start).equals(counter)) {
                    return false;
                }
                start++;
            }
        }
        return values.size() == start;
    }

    public int countSigns(char[] parsedKey) {
        int amount = 0;
        for (char c : parsedKey) {
            if (c == '?') {
                amount++;
            }
        }
        return amount;
    }

    public Set<List<Object>> getAllCombinationsOfSigns(int amount, List<Integer> values) {
        Set<List<Object>> allCombinations = new HashSet<>();
        int maxValue = values.stream().max(Integer::compareTo).get();
        List<Object> list;
        for (int i = 0; i <= amount; i++) {
            if (i > maxValue) {
                continue;
            }
            list = new ArrayList<>();
            for (int q = 0; q < i; q++) {
                list.add('#');
            }
            for (int c = 0; c < amount - i; c++) {
                list.add('.');
            }
            allCombinations.addAll(CombinatorialList.getAllCombinations(list));
        }
        return allCombinations;
    }
}