package aggregation.functions;

import model.Pair;

import java.util.List;

public interface ArithmeticAggregation {
    double aggregate(List<Pair> filteredData);
}
