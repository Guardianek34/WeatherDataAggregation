package aggregation.aggregate;

import aggregation.filters.FilterPipeline;
import aggregation.functions.AggregateFunction;
import aggregation.functions.SumFunction;
import model.Pair;

import java.util.List;

public class DivideAggregate extends Aggregate {
    private final Integer size = 0;

    public DivideAggregate(FilterPipeline<List<Pair>, List<Pair>> pipeline,
                           TimeSpan timeSpan) {
        super(pipeline, new AggregateFunction(new SumFunction()), timeSpan);
    }

    @Override
    public void aggregate(List<Pair> filteredData) {
        Double newlyCalculatedValue = calculateNewValue(filteredData);
        this.calculatedValue = (this.calculatedValue + newlyCalculatedValue) / (size + filteredData.size());
    }
}
