package aggregation.factories;

import aggregation.aggregate.Aggregate;
import aggregation.aggregate.TimeFrame;
import aggregation.filters.*;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AggregateFactory {

    public Aggregate createAggregate(Optional<LocalDateTime> startTime,
                                     Optional<LocalDateTime> endTime,
                                     Optional<LocationStage> locationStage,
                                     AggregateFunction aggregateFunction){

        TimeFrame timeFrame = new TimeFrameFactory().createDateRange(startTime, endTime);

        FilterPipeline<List<Pair>, List<Pair>> filterPipeline =
                new PipelineFactory().createPipeline(
                        timeFrame,
                        locationStage
                );

        return new Aggregate(filterPipeline, aggregateFunction, timeFrame);
    }
}
