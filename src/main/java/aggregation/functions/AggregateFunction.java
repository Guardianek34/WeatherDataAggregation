package aggregation.functions;

import model.Pair;

import java.util.List;

public class AggregateFunction {

    private ArithmeticAggregation function;

    public AggregateFunction(ArithmeticAggregation function) {
        this.function = function;
    }

    public Double calculateValue(List<Pair> filteredData){
        return function.aggregate(filteredData);
    }
}
