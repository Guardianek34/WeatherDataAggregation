package aggregation.functions;

import model.Pair;

import java.util.List;

public class AggregateFunction {

    private final AggregationStrategy function;

    public AggregateFunction(AggregationStrategy function) {
        this.function = function;
    }

    public Double calculateValue(List<Pair> filteredData){
        return function.aggregate(filteredData);
    }
}
