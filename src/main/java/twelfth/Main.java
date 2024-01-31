package twelfth;

import twelfth.part1.HotSprings;
import twelfth.part2.HotSprings2;


public class Main {
    public static void main(String[] args) {
        HotSprings hotSprings = new HotSprings("input12.txt");
        HotSprings2 hotSprings2 = new HotSprings2("input12.txt");
        // part1
        System.out.println(hotSprings.getSumOfAll());
        // part2
        System.out.println(hotSprings2.getSumOfAll());


    }
}
