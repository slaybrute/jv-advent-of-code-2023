package seventh;

import seventh.part1.CamelCards;
import seventh.part2.CamelCards2;

public class Main {
    public static void main(String[] args) {
        CamelCards camelCards = new CamelCards("input7.txt");
        CamelCards2 camelCards2 = new CamelCards2("input7.txt");
        //part1
        System.out.println(camelCards.getSumOfAllWins());
        //part2
        System.out.println(camelCards2.getSumOfAllWins());
    }
}
