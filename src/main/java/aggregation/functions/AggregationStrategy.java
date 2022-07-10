package aggregation.functions;

import model.Pair;

import java.util.List;

public interface AggregationStrategy {
    double aggregate(List<Pair> filteredData);
}
