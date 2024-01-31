package sixths.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.ArrayList;
import java.util.List;

public class BoatRace {
    private final FileReader fileReader;
    private List<String> data;
    private String fileName;
    protected List<Integer> times;
    protected List<Integer> distances;

    {
        fileReader = new FileReaderImpl();
        times = new ArrayList<>();
        distances = new ArrayList<>();
    }

    public BoatRace(String fileName) {
        this.fileName = fileName;
        setData();
        parseData();
    }

    public void setData() {
        data = fileReader.read(fileName);
    }

    public void parseData() {
        String[] timeArray = data.get(0).split(" ");
        String[] distanceArray = data.get(1).split(" ");
        for (int i = 1; i < timeArray.length; i++) {
            if (!timeArray[i].isEmpty()) {
                times.add(Integer.parseInt(timeArray[i]));
            }
        }
        for (int i = 1; i < distanceArray.length; i++) {
            if (!distanceArray[i].isEmpty()) {
                distances.add(Integer.parseInt(distanceArray[i]));
            }
        }
    }

    public List<List<Integer>> getTimesForBeatTheRecord() {
        List<List<Integer>> timesForBeat = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int q = 1; q < times.get(i) - 1; q++) {
                if ((times.get(i) - q) * q > distances.get(i)) {
                    tmp.add(q);
                }
            }
            timesForBeat.add(tmp);
        }
        return timesForBeat;
    }

    public int getMultiplyOfAllAmount() {
        int amount = 1;
        for (List<Integer> times : getTimesForBeatTheRecord()) {
            amount *= times.size();
        }
        return amount;
    }
}
