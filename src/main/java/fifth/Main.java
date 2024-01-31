package fifth;

import fifth.part1.Fertilizer;
import fifth.part2.Fertilizer2;

public class Main {
    public static void main(String[] args) {
        Fertilizer fertilizer = new Fertilizer("input5.txt");
        Fertilizer2 fertilizer2 = new Fertilizer2("input5.txt");
        // part1
        System.out.println(fertilizer.getLowestLocation());
        // part2
        System.out.println(fertilizer2.getLowestLocation());
    }
}
