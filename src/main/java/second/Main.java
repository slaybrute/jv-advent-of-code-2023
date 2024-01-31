package second;

import second.part1.CubeConundrum;
import second.part2.CubeConundrum2;
import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReaderImpl();
        CubeConundrum cubeConundrum = new CubeConundrum("input2.txt");
        CubeConundrum2 cubeConundrum1 = new CubeConundrum2("input2.txt");
        //part1
        System.out.println(cubeConundrum.getSumOfPossibleGames());
        //part2
        System.out.println(cubeConundrum1.getSumOfPowersMinSet());
    }
}
