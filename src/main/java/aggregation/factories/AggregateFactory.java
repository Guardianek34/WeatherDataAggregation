package aggregation.factories;

import aggregation.aggregate.AverageAggregate;
import aggregation.aggregate.SumAggregate;
import aggregation.aggregate.TimeSpan;
import aggregation.filters.FilterPipeline;
import aggregation.filters.LocationStage;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.util.List;

public class AggregateFactory {

    public AverageAggregate createAverageAggregate(TimeSpan timeSpan,
                                                   LocationStage locationStage) {
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, locationStage);
        return new AverageAggregate(filterPipeline, timeSpan);
    }

    public AverageAggregate createAverageAggregate(TimeSpan timeSpan) {
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, null);
        return new AverageAggregate(filterPipeline, timeSpan);
    }

    public SumAggregate createSumAggregate(TimeSpan timeSpan,
                                           LocationStage locationStage,
                                           AggregateFunction aggregateFunction) {
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, locationStage);
        return new SumAggregate(filterPipeline, aggregateFunction, timeSpan);
    }

    public SumAggregate createSumAggregate(TimeSpan timeSpan,
                                           AggregateFunction aggregateFunction) {
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, null);
        return new SumAggregate(filterPipeline, aggregateFunction, timeSpan);
    }


}
