package aggregation.filters;

import model.Pair;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WHERE cityName = "exampleCity"
 */
public class LocationStage implements Stage<List<Pair>, List<Pair>> {

    private final String cityName;

    public LocationStage(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public List<Pair> process(List<Pair> input) {
        return input.stream()
                .filter(p -> p.getCityName().equals(cityName))
                .collect(Collectors.toList());
    }
}
