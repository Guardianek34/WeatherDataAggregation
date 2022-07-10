package aggregation.functions;

import model.Pair;

import java.util.List;

public class SumFunction implements AggregationStrategy {
    @Override
    public double aggregate(List<Pair> filteredData) {
        return filteredData.stream()
                .mapToDouble(Pair::getValue)
                .sum();
    }
}
