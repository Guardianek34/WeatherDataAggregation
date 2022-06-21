package aggregation.functions;

import model.Pair;

import java.util.List;

public class ArithmeticAverageFunction implements ArithmeticAggregation{

    @Override
    public Double aggregate(List<Pair> filteredData) {
        return filteredData.stream().mapToDouble(o -> o.getValue()).average().orElse(0);
    }
}
