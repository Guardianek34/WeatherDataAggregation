package aggregation.functions;

import model.Pair;

import java.util.List;

public class SumFunction implements ArithmeticAggregation{
    @Override
    public Double aggregate(List<Pair> filteredData) {
        return filteredData.stream()
                .mapToDouble(p -> p.getValue())
                .sum();
    }
}
