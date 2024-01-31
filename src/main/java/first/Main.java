package first;

import first.part1.Trebuchet;
import first.part2.Trebuchet2;

public class Main {
    public static void main(String[] args) {
        Trebuchet trebuchet = new Trebuchet("input1.txt");
        Trebuchet2 trebuchet2 = new Trebuchet2("input1.txt");
        // part1
        System.out.println(trebuchet.getSumOfCalibratedNumbers());
        // part2
        System.out.println(trebuchet2.getSumOfCalibratedNumbers());
    }
}
