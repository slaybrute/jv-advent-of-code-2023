package ninth.part2;

import ninth.part1.MirageMaintenance;

import java.util.ArrayList;
import java.util.List;

public class MirageMaintenance2 extends MirageMaintenance {
    public MirageMaintenance2(String fileName) {
        super(fileName);
    }
    @Override
    public int getNextValue(List<Integer> history ) {
        List<Integer> lastValues = new ArrayList<>();
        while(!isAllValuesZero(history)) {
            lastValues.add(history.get(0));
            List<Integer> tmp = new ArrayList<>();
            for (int i = 1; i < history.size(); i++) {
                tmp.add(history.get(i) - history.get(i - 1));
            }
            history = tmp;
        }
        int difference = lastValues.get(lastValues.size() - 1);
        for (int i = lastValues.size() - 2; i >= 0; i --) {
            difference = lastValues.get(i) - difference;
        }
        return difference;
    }
}
