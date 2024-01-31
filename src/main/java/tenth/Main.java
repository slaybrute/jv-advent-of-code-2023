package tenth;

import tenth.part1.PipeMaze;
import tenth.part2.PipeMaze2;

public class Main {
    public static void main(String[] args) {
        PipeMaze pipeMaze = new PipeMaze("input10.txt");
        PipeMaze2 pipeMaze2 = new PipeMaze2("input10.txt");
        // part
        System.out.println(pipeMaze.getFarPoint());
        // part2
        System.out.println(pipeMaze2.getAmountOfPointsInLoop());
    }
}
