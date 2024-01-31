package eighth;

import eighth.part1.HauntedWasteland;
import eighth.part2.HauntedWasteland2;

public class Main {
    public static void main(String[] args) {
        HauntedWasteland hauntedWasteland = new HauntedWasteland("input8.txt");
        HauntedWasteland2 hauntedWasteland2 = new HauntedWasteland2("input8.txt");
        // part1
        System.out.println(hauntedWasteland.getAmountOfMoves());
        // part2
        System.out.println(hauntedWasteland2.getAmountOfMoves());
    }
}
