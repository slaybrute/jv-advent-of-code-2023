package eighth.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.*;

public class HauntedWasteland {
    private static final FileReader FILE_READER;
    private String fileName;
    private List<String> data;
    private String startNode;
    protected Set<String> startNodes;
    protected char[] route;
    protected Map<String, List<String>> nodes = new HashMap<>();

    static {
        FILE_READER = new FileReaderImpl();
    }

    {
        startNodes = new HashSet<>();
    }

    public HauntedWasteland(String fileName) {
        this.fileName = fileName;
        setData();
    }

    public void setData() {
        data = FILE_READER.read(fileName);
        setParsedData();
    }

    public void setParsedData() {
        route = data.get(0).toCharArray();
        startNode = data.get(2).split(" ")[0];
        for (int i = 2; i < data.size(); i++) {
            String[] splitInput = data.get(i).split(" ");
            StringBuilder stringBuilder = new StringBuilder(splitInput[2] + " " + splitInput[3]);
            stringBuilder.delete(0, 1);
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            stringBuilder.delete(stringBuilder.indexOf(","), stringBuilder.indexOf(",") + 1);
            String[] splitInput1 = stringBuilder.toString().split(" ");
            nodes.put(splitInput[0], new ArrayList<>(List.of(splitInput1[0], splitInput1[1])));
        }
    }

    public long getAmountOfMoves() {
        long amount = 0;
        String next = "AAA";
        while (true) {
            for (char c : route) {
                if (next.equals("ZZZ")) {
                    return amount;
                }
                if (c == 'R') {
                    next = nodes.get(next).get(1);
                } else {
                    next = nodes.get(next).get(0);
                }
                amount++;
            }
        }
    }
}
