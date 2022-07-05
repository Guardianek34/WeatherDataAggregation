package aggregation.functions;

import model.Pair;

import java.util.List;

public class CountFunction implements ArithmeticAggregation {
    @Override
    public double aggregate(List<Pair> filteredData) {
        return filteredData.size();
    }
}
