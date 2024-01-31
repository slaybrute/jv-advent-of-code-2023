package thirteenth;

import thirteenth.part1.PointIncidence;
import thirteenth.part2.PointIncidence2;

public class Main {
    public static void main(String[] args) {
        PointIncidence pointIncidence = new PointIncidence("input13.txt");
        PointIncidence2 pointIncidence2 = new PointIncidence2("input13.txt");
        // part1
        System.out.println(pointIncidence.getSumOfAllReflections());
        // part2
        System.out.println(pointIncidence2.getSumOfAllReflections());
    }
}

