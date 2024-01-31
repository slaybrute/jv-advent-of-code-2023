package tenth.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PipeMaze {
    private String fileName;
    private static final FileReader FILE_READER;
    protected static final Map<Character, List<List<Integer>>> ROUTE;
    protected final int[] startNode = new int[2];
    protected char[][] parsedPath;
    protected List<Integer> rightNext;

    static {
        FILE_READER = new FileReaderImpl();
        ROUTE = new HashMap<>() {{
            put('|', List.of(List.of(1, 0), List.of(-1, 0)));
            put('-', List.of(List.of(0, 1), List.of(0, -1)));
            put('L', List.of(List.of(-1, 0), List.of(0, 1)));
            put('J', List.of(List.of(-1, 0), List.of(0, -1)));
            put('7', List.of(List.of(1, 0), List.of(0, -1)));
            put('F', List.of(List.of(1, 0), List.of(0, 1)));
        }};
    }

    public PipeMaze(String fileName) {
        this.fileName = fileName;
        setParsedPath();
    }

    public void setParsedPath() {
        List<String> data = FILE_READER.read(fileName);
        parsedPath = new char[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            char[] tmp = data.get(i).toCharArray();
            System.arraycopy(tmp, 0, parsedPath[i], 0, tmp.length);
        }
    }

    public void setStartNode() {
        for (int i = 0; i < parsedPath.length; i++) {
            for (int q = 0; q < parsedPath[i].length; q++) {
                if (parsedPath[i][q] == 'S') {
                    startNode[0] = i;
                    startNode[1] = q;
                }
            }
        }
    }

    public int getAmountOfSteps(List<Integer> next) {
        int count = 1;
        List<Integer> prev = new ArrayList<>(List.of(this.startNode[0], this.startNode[1]));
        List<Integer> current = new ArrayList<>(List.of(next.get(0), next.get(1)));
        do {
            try {
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
                            break;
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            } catch (NullPointerException e) {
                return 0;
            }
            count++;
        }
        while (parsedPath[current.get(0)][current.get(1)] != 'S');
        return count;
    }

    public int getFarPoint() {
        setStartNode();
        rightNext = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        List<List<Integer>> nexts = new ArrayList<>(List.of(
                List.of(startNode[0], startNode[1] + 1), List.of(startNode[0], startNode[1] - 1),
                List.of(startNode[0] + 1, startNode[1]), List.of(startNode[0] - 1, startNode[1])));
        Map<Integer, List<Integer>> tmp1 = new HashMap<>();
        for (List<Integer> next : nexts) {
            try {
                results.add(getAmountOfSteps(next));
                tmp1.put(getAmountOfSteps(next), next);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        Map<Integer, Integer> tmp = new HashMap<>();// Steps ; Amount
        for (int result : results) {
            tmp.merge(result, 1, Integer::sum);
        }
        tmp.remove(0);
        for (int key : tmp.keySet()) {
            if (tmp.get(key) == 2) {
                rightNext = tmp1.get(key);
                return key / 2;
            }
        }
        return 0;
    }
}
