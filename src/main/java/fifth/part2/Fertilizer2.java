package fifth.part2;

import fifth.part1.Fertilizer;

import java.util.List;

public class Fertilizer2 extends Fertilizer {

    public Fertilizer2(String fileName) {
        super(fileName);
    }

    @Override
    public void fillSeedLocation() {
        super.setSeeds();
        //---------------------------------
        for (int i = 0; i < seeds.size(); i += 2) {
            long first = seeds.get(i);
            long second = seeds.get(i) + seeds.get(i + 1) - 1;
            int increment = 100000000;
            long firstSeedLocation = findSeedLocation(first);
            long secondSeedLocation = findSeedLocation(second);
            if (secondSeedLocation < firstSeedLocation) {
                while (increment != 0) {
                    if (secondSeedLocation > findSeedLocation(second - increment)) {
                        secondSeedLocation = findSeedLocation(second - increment);
                        second = second - increment;
                    } else {
                        increment /= 2;
                    }
                }
                seedsLocation.add(secondSeedLocation);
            } else {
                while (increment != 0) {
                    if (firstSeedLocation > findSeedLocation(first + increment)) {
                        firstSeedLocation = findSeedLocation(first + increment);
                        first = first + increment;
                    } else {
                        increment /= 2;
                    }
                }
                seedsLocation.add(firstSeedLocation);
            }
        }
    }

    public long findSeedLocation(Long seed) {
        for (List<Long> soils : seedToSoil) {
            if (seed >= soils.get(1) && seed <= soils.get(1) + soils.get(2) - 1) {
                seed = seed + soils.get(0) - soils.get(1);
                break;
            }
        }
        for (List<Long> fertilizers : soilToFertilizer) {
            if (seed >= fertilizers.get(1) && seed <= fertilizers.get(1) + fertilizers.get(2) - 1) {
                seed = seed + fertilizers.get(0) - fertilizers.get(1);
                break;
            }
        }
        for (List<Long> waters : fertilizerToWater) {
            if (seed >= waters.get(1) && seed <= waters.get(1) + waters.get(2) - 1) {
                seed = seed + waters.get(0) - waters.get(1);
                break;
            }
        }
        for (List<Long> lights : waterToLight) {
            if (seed >= lights.get(1) && seed <= lights.get(1) + lights.get(2) - 1) {
                seed = seed + lights.get(0) - lights.get(1);
                break;
            }
        }
        for (List<Long> temperatures : lightToTemperature) {
            if (seed >= temperatures.get(1) && seed <= temperatures.get(1) + temperatures.get(2) - 1) {
                seed = seed + temperatures.get(0) - temperatures.get(1);
                break;
            }
        }
        for (List<Long> humidity : temperatureToHumidity) {
            if (seed >= humidity.get(1) && seed <= humidity.get(1) + humidity.get(2) - 1) {
                seed = seed + humidity.get(0) - humidity.get(1);
                break;
            }
        }
        for (List<Long> location : humidityToLocation) {
            if (seed >= location.get(1) && seed <= location.get(1) + location.get(2) - 1) {
                seed = seed + location.get(0) - location.get(1);
                break;
            }
        }
        return seed;
    }
}

