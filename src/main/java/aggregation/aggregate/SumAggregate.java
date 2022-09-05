package aggregation.aggregate;

import aggregation.filters.FilterPipeline;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.util.List;

public class SumAggregate extends Aggregate {
    public SumAggregate(FilterPipeline<List<Pair>, List<Pair>> pipeline,
                        AggregateFunction arithmeticMethod,
                        TimeSpan timeSpan) {
        super(pipeline, arithmeticMethod, timeSpan);
    }

    @Override
    public void aggregate(List<Pair> filteredData) {
        Double newlyCalculatedValue = calculateNewValue(filteredData);
        this.calculatedValue += newlyCalculatedValue;
    }
}
