package aggregation.filters;

import model.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class LocationStep implements Step<List<Pair>, List<Pair>> {

    private final String cityName;

    public LocationStep(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public List<Pair> process(List<Pair> input) {
        return input.stream()
                .filter(p -> p.getLocation().equals(cityName))
                .collect(Collectors.toList());
    }
}
