package fourth;

import fourth.part1.Scratchcards;
import fourth.part2.Scratchcards2;

public class Main {
    public static void main(String[] args) {
        Scratchcards scratchcards = new Scratchcards("input4.txt");
        Scratchcards2 scratchcards2 = new Scratchcards2("input4.txt");
        // part1
        System.out.println(scratchcards.getSumOfMatchedNumbers());
        // part2
        System.out.println(scratchcards2.getSumOfAllCopies());

    }
}
