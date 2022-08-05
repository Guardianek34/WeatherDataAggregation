package aggregation.factories;

import aggregation.aggregate.Aggregate;
import aggregation.aggregate.TimeSpan;
import aggregation.filters.*;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AggregateFactory {

    public Aggregate createAggregate(TimeSpan timeSpan,
                                     LocationStage locationStage,
                                     AggregateFunction aggregateFunction){
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new PipelineFactory()
                .createPipelineByLocationTime(timeSpan, locationStage);
        return new Aggregate(filterPipeline, aggregateFunction, timeSpan);
    }
}
