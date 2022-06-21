package aggregation.functions;

import model.Pair;

import java.util.List;

public interface ArithmeticAggregation {
    Double aggregate(List<Pair> filteredData);
}
