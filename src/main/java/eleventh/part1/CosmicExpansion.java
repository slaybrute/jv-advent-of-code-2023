package eleventh.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CosmicExpansion {
    private static final FileReader FILE_READER;
    private String fileName;
    private List<List<Character>> data;

    protected Map<Integer, List<Integer>> galacticCoordinates;

    static {
        FILE_READER = new FileReaderImpl();
    }

    {
        galacticCoordinates = new HashMap<>();
    }

    public CosmicExpansion(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        data = new ArrayList<>();
        for (String s : FILE_READER.read(fileName)) {
            List<Character> tmp = new ArrayList<>();
            for (char c : s.toCharArray()) {
                tmp.add(c);
            }
            data.add(tmp);
        }
    }

    public void extendGalactic() {
        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).contains('#')) {
                List<Character> tmp = new ArrayList<>(data.get(i));
                data.add(i, tmp);
                i++;
            }
        }
        for (int i = 0; i < data.get(0).size(); i++) {
            int counter = 0;
            for (List<Character> datum : data) {
                if (datum.get(i) != '#') {
                    counter++;
                }
            }
            if (counter == data.size()) {
                for (List<Character> datum : data) {
                    datum.add(i, '.');
                }
                i++;
            }
        }
    }

    public void fillGalacticCoordinates() {
        int startGalactic = 1;
        for (int i = 0; i < data.size(); i++) {
            for (int q = 0; q < data.get(i).size(); q++) {
                if (data.get(i).get(q) == '#') {
                    galacticCoordinates.put(startGalactic, List.of(i, q));
                    startGalactic++;
                }
            }
        }
    }

    public long getSumOfAllDistancesBetweenGalactic() {
        extendGalactic();
        fillGalacticCoordinates();
        long sum = 0;
        for (int i = 1; i < galacticCoordinates.size(); i++) {
            for (int q = i + 1; q <= galacticCoordinates.size(); q++) {
                sum += Math.abs(galacticCoordinates.get(q).get(0) - galacticCoordinates.get(i).get(0))
                        + Math.abs(galacticCoordinates.get(q).get(1) - galacticCoordinates.get(i).get(1));
            }
        }
        return sum;
    }
}
