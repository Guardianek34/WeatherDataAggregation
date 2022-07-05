package aggregation.aggregate;

import aggregation.filters.AfterDateStage;
import aggregation.filters.BeforeDateStage;
import aggregation.filters.FilterPipeline;
import aggregation.filters.NoOperationStage;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AggregateFactory {

    public Aggregate createAggregate(Optional<LocalDateTime> startTime,
                                     Optional<LocalDateTime> endTime,
                                     AggregateFunction aggregateFunction){
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new FilterPipeline<>(new NoOperationStage());
        DateRange dateRange = createFiltersInSpecificTimeFrame(startTime, endTime, filterPipeline);

        return new Aggregate(filterPipeline, aggregateFunction, dateRange);
    }

    public DateRange createFiltersInSpecificTimeFrame(Optional<LocalDateTime> startTime,
                                                      Optional<LocalDateTime> endTime,
                                                      FilterPipeline<List<Pair>, List<Pair>> filterPipeline){
        boolean isStartTimePassed = startTime.isPresent();
        boolean isEndTimePassed = endTime.isPresent();
        DateRange dateRange;

        if(isStartTimePassed && isEndTimePassed){
            dateRange = new DateRange(startTime.get(), endTime.get());
            filterPipeline.addStep(new BeforeDateStage(endTime.get()));
            filterPipeline.addStep(new AfterDateStage(startTime.get()));
        } else if (isStartTimePassed){
            dateRange = new DateRange(startTime.get(), LocalDateTime.MAX);
            filterPipeline.addStep(new AfterDateStage(startTime.get()));
        } else if (isEndTimePassed) {
            dateRange = new DateRange(LocalDateTime.MIN, endTime.get());
            filterPipeline.addStep(new BeforeDateStage(endTime.get()));
        } else {
            dateRange = new DateRange(LocalDateTime.MIN, LocalDateTime.MAX);
        }

        return dateRange;
    }

}
