package third;


import third.part1.GearRatios;
import third.part2.GearRatios2;
import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        GearRatios gearRatios = new GearRatios("input3.txt");
        GearRatios2 gearRatios2 = new GearRatios2("input3.txt");
        //part1
        System.out.println(gearRatios.getSumOfAllGearNumbers());
        //part2
        System.out.println(gearRatios2.getSumOfAllGearNumbers());
    }
}
