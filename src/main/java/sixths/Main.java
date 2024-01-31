package sixths;

import sixths.part1.BoatRace;
import sixths.part2.BoatRace2;


public class Main {
    public static void main(String[] args) {
        BoatRace boatRace = new BoatRace("input6.txt");
        BoatRace2 boatRace2 = new BoatRace2("input6.txt");
        // part1
        System.out.println(boatRace.getMultiplyOfAllAmount());
        // part2
        System.out.println(boatRace2.getTimeForBeatTheRecord());
    }
}
