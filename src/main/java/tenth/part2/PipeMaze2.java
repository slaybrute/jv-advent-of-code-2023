package tenth.part2;

import tenth.part1.PipeMaze;

import java.util.*;

public class PipeMaze2 extends PipeMaze {
    private List<List<Integer>> allPoints;

    {
        allPoints = new ArrayList<>();
    }

    public PipeMaze2(String fileName) {
        super(fileName);
    }

    public void fillAllPoints() {
        List<Integer> prev = new ArrayList<>(List.of(this.startNode[0], this.startNode[1]));
        List<Integer> current = new ArrayList<>(List.of(rightNext.get(0), rightNext.get(1)));
        allPoints.add(new ArrayList<>(List.of(startNode[0], startNode[1])));
        allPoints.add(new ArrayList<>(List.of(current.get(0), current.get(1))));
        do {
            for (List<Integer> integers : ROUTE.get(parsedPath[current.get(0)][current.get(1)])) {
                try {
                    if (parsedPath[current.get(0) + integers.get(0)][current.get(1) + integers.get(1)] != '.'
                            && (current.get(0) + integers.get(0) != prev.get(0)
                            || current.get(1) + integers.get(1) != prev.get(1))) {
                        prev.clear();
                        prev.add(current.get(0));
                        prev.add(current.get(1));
                        current.clear();
                        current.add(prev.get(0) + integers.get(0));
                        current.add(prev.get(1) + integers.get(1));
                        allPoints.add(new ArrayList<>(List.of(current.get(0), current.get(1))));
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        while (parsedPath[current.get(0)][current.get(1)] != 'S');
        allPoints.remove(allPoints.size() - 1);
    }

    public int getAmountOfPointsInLoop() {
        getFarPoint();
        fillAllPoints();
        int amount = 0;
        for (int i = 0; i < parsedPath.length; i++) {
            for (int q = 0; q < parsedPath[i].length; q++) {
                if (!allPoints.contains(List.of(i, q))) {
                    if (isPointInTheLoop(i, q)) {
                        amount++;
                    }
                }
            }
        }
        return amount;
    }

    public boolean isPointInTheLoop(int i, int q) {
        int amount = 0;
        while (i > 0) {
            i--;
            if (allPoints.contains(List.of(i, q))) {
                int current = allPoints.indexOf(List.of(i, q));
                int prev = allPoints.indexOf(List.of(i, q)) - 1;
                int next = allPoints.indexOf(List.of(i, q)) + 1;
                if (allPoints.indexOf(List.of(i, q)) == allPoints.size() - 1) {
                    next = 0;
                } else if (allPoints.indexOf(List.of(i, q)) == 0) {
                    prev = allPoints.size() - 1;
                }
                if (!Objects.equals(allPoints.get(next).get(1), allPoints.get(prev).get(1))
                        && Objects.equals(allPoints.get(next).get(0), allPoints.get(prev).get(0))) {
                    amount++;
                } else if (!Objects.equals(allPoints.get(next).get(1), allPoints.get(prev).get(1))
                        && !Objects.equals(allPoints.get(next).get(0), allPoints.get(prev).get(0))) {
                    if (allPoints.get(next).get(1) < allPoints.get(current).get(1)
                            || allPoints.get(prev).get(1) < allPoints.get(current).get(1)) {
                        amount++;
                    }
                }
            }
        }
        return amount % 2 != 0;
    }
}
