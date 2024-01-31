package twelfth.part2;

import twelfth.part1.HotSprings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HotSprings2 extends HotSprings {
    public HotSprings2(String fileName) {
        super(fileName);
    }

    @Override
    public void setData() {
        for (String s : FILE_READER.read(fileName)) {
            String firstPart = s.split(" ")[0];
            String[] secondPart = s.split(" ")[1].split(",");
            //----------------------
            StringBuilder key = new StringBuilder();
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                for (String number : secondPart) {
                    values.add(Integer.parseInt(number));
                }
                key.append(firstPart);
            }
            data.put(key.toString(), values);
        }
    }
}
