package twelfth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinatorialList {
    public static Set<List<Object>> getAllCombinations(List<?> objects) {
        Set<List<Object>> shuffledObjects = new HashSet<>();
        if (objects.size() == 2) {
            shuffledObjects.add(new ArrayList<>(List.of(objects.get(0), objects.get(1))));
            shuffledObjects.add(new ArrayList<>(List.of(objects.get(1), objects.get(0))));
            return shuffledObjects;
        }
        int size = objects.size();
        for (int i = 0; i < size; i++) {
            List<Object> tmpObjects = new ArrayList<>(size - 1);
            for (int q = 0; q < size; q++) {
                if (q != i) {
                    tmpObjects.add(objects.get(q));
                }
            }
            for (List<Object> object : getAllCombinations(tmpObjects)) {
                tmpObjects = new ArrayList<>();
                tmpObjects.add(objects.get(i));
                tmpObjects.addAll(object);
                shuffledObjects.add(tmpObjects);
            }
        }
        return shuffledObjects;
    }
}
