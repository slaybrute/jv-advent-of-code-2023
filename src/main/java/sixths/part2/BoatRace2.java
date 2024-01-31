package sixths.part2;

import sixths.part1.BoatRace;

public class BoatRace2 extends BoatRace {
    private long time;
    private long distance;

    public BoatRace2(String fileName) {
        super(fileName);
    }

    public void parseData() {
        super.parseData();
        StringBuilder distanceBuilder = new StringBuilder();
        StringBuilder timeBuilder = new StringBuilder();
        for (int distance : distances) {
            distanceBuilder.append(distance);
        }
        for (int time : times) {
            timeBuilder.append(time);
        }
        this.time = Long.parseLong(timeBuilder.toString());
        this.distance = Long.parseLong(distanceBuilder.toString());
    }

    public int getTimeForBeatTheRecord() {
        int counter = 0;
        for (int q = 1; q < time - 1; q++) {
            if ((time - q) * q > distance) {
                counter++;
            }
        }
        return counter;
    }
}
