package aggregation.factories;

import aggregation.aggregate.Aggregate;
import aggregation.aggregate.DivideAggregate;
import aggregation.aggregate.SumAggregate;
import aggregation.aggregate.TimeSpan;
import aggregation.filters.*;
import aggregation.functions.AggregateFunction;
import aggregation.functions.SumFunction;
import model.Pair;

import java.util.List;

public class AggregateFactory {

    public DivideAggregate createDivideAggregate(TimeSpan timeSpan,
                                                 LocationStage locationStage){
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, locationStage);
        return new DivideAggregate(filterPipeline, timeSpan);
    }

    public SumAggregate createSumAggregate(TimeSpan timeSpan,
                                           LocationStage locationStage,
                                           AggregateFunction aggregateFunction){
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, locationStage);
        return new SumAggregate(filterPipeline, aggregateFunction, timeSpan);
    }

}
