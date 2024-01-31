package eleventh.part2;

import eleventh.part1.CosmicExpansion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CosmicExpansion2 extends CosmicExpansion {
    private Map<Integer, List<Integer>> prevGalacticCoordinates;

    public CosmicExpansion2(String fileName) {
        super(fileName);
    }

    @Override
    public void fillGalacticCoordinates() {
        super.fillGalacticCoordinates();
        prevGalacticCoordinates = new HashMap<>(galacticCoordinates);
        extendGalactic();
        super.fillGalacticCoordinates();
        for (Integer key : galacticCoordinates.keySet()) {
            List<Integer> newCoord = new ArrayList<>(2);
            newCoord.add(prevGalacticCoordinates.get(key).get(0)
                    + (galacticCoordinates.get(key).get(0) - prevGalacticCoordinates.get(key).get(0)) * 999_999);
            newCoord.add(prevGalacticCoordinates.get(key).get(1)
                    + (galacticCoordinates.get(key).get(1) - prevGalacticCoordinates.get(key).get(1)) * 999_999);
            galacticCoordinates.put(key, newCoord);
        }
    }
}
