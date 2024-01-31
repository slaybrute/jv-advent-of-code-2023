package eleventh;

import eleventh.part1.CosmicExpansion;
import eleventh.part2.CosmicExpansion2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CosmicExpansion cosmicExpansion = new CosmicExpansion("input11.txt");
        CosmicExpansion2 cosmicExpansion2 = new CosmicExpansion2("input11.txt");
        // part1
        System.out.println(cosmicExpansion.getSumOfAllDistancesBetweenGalactic());
        // part2
        System.out.println(cosmicExpansion2.getSumOfAllDistancesBetweenGalactic());
    }
}
