package fifth.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fertilizer {
    private static final FileReader FILE_READER;
    private String fileName;
    private List<String> data;
    protected final List<Long> seeds;
    protected final Set<Long> seedsLocation;
    protected final List<List<Long>> seedToSoil;
    protected final List<List<Long>> soilToFertilizer;
    protected final List<List<Long>> fertilizerToWater;
    protected final List<List<Long>> waterToLight;
    protected final List<List<Long>> lightToTemperature;
    protected final List<List<Long>> temperatureToHumidity;
    protected final List<List<Long>> humidityToLocation;

    static {
        FILE_READER = new FileReaderImpl();
    }

    {
        seeds = new ArrayList<>();
        seedsLocation = new HashSet<>();
        seedToSoil = new ArrayList<>();
        soilToFertilizer = new ArrayList<>();
        fertilizerToWater = new ArrayList<>();
        waterToLight = new ArrayList<>();
        lightToTemperature = new ArrayList<>();
        temperatureToHumidity = new ArrayList<>();
        humidityToLocation = new ArrayList<>();
    }

    public Fertilizer(String fileName) {
        this.fileName = fileName;
        setData();
        parseData();
    }

    public void setData() {
        this.data = FILE_READER.read(fileName);
    }

    public void parseData() {
        boolean isSeedToSoil = false;
        boolean isSoilToFertilizer = false;
        boolean isFertilizerToWater = false;
        boolean isWaterToLight = false;
        boolean isLightToTemperature = false;
        boolean isTemperatureToHumidity = false;
        boolean isHumidityToLocation = false;
        for (String s : data) {
            List<Long> tmp = new ArrayList<>();
            if (s.contains("seed-to-soil")) {
                isSeedToSoil = true;
                continue;
            } else if (s.contains("soil-to-fertilizer")) {
                isSoilToFertilizer = true;
                isSeedToSoil = false;
                continue;
            } else if (s.contains("fertilizer-to-water")) {
                isFertilizerToWater = true;
                isSoilToFertilizer = false;
                continue;
            } else if (s.contains("water-to-light")) {
                isWaterToLight = true;
                isFertilizerToWater = false;
                continue;
            } else if (s.contains("light-to-temperature")) {
                isLightToTemperature = true;
                isWaterToLight = false;
                continue;
            } else if (s.contains("temperature-to-humidity")) {
                isTemperatureToHumidity = true;
                isLightToTemperature = false;
                continue;
            } else if (s.contains("humidity-to-location")) {
                isHumidityToLocation = true;
                isTemperatureToHumidity = false;
                continue;
            }
            if (isSeedToSoil) {
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    seedToSoil.add(tmp);
                }
            } else if (isSoilToFertilizer) {
                tmp = new ArrayList<>();
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    soilToFertilizer.add(tmp);
                }
            } else if (isFertilizerToWater) {
                tmp = new ArrayList<>();
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    fertilizerToWater.add(tmp);
                }
            } else if (isWaterToLight) {
                tmp = new ArrayList<>();
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    waterToLight.add(tmp);
                }
            } else if (isLightToTemperature) {
                tmp = new ArrayList<>();
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    lightToTemperature.add(tmp);
                }
            } else if (isTemperatureToHumidity) {
                tmp = new ArrayList<>();
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    temperatureToHumidity.add(tmp);
                }
            } else if (isHumidityToLocation) {
                tmp = new ArrayList<>();
                for (String number : s.split(" ")) {
                    if (!number.isEmpty()) {
                        tmp.add(Long.parseLong(number));
                    }
                }
                if (!tmp.isEmpty()) {
                    humidityToLocation.add(tmp);
                }
            }
        }
    }

    public void setSeeds() {
        for (String s : data.get(0).split(":")[1].split(" ")) {
            if (!s.isEmpty()) {
                seeds.add(Long.parseLong(s));
            }
        }
    }

    public void fillSeedLocation() {
        setSeeds();
        for (long seed : seeds) {
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
            seedsLocation.add(seed);
        }
    }

    public long getLowestLocation() {
        fillSeedLocation();
        return seedsLocation.stream().min(Long::compareTo).orElse(0L);
    }
}
