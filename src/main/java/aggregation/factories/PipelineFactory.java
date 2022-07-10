package aggregation.factories;

import aggregation.aggregate.TimeFrame;
import aggregation.filters.*;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PipelineFactory {

    public FilterPipeline<List<Pair>, List<Pair>> createPipeline(TimeFrame timeFrame,
                                                                 Optional<LocationStage> locationStage){

        // using LocalDateTime.MIN and MAX to represent whether values are present (instead of null values)
        // look at TimeFrameFactory
        boolean isStartTimePresent = !timeFrame.getStartDate().isEqual(LocalDateTime.MIN);
        boolean isEndTimePresent = !timeFrame.getStartDate().isEqual(LocalDateTime.MAX);
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new FilterPipeline<>(new NoOperationStage());

        // stages correlated to time
        if(isStartTimePresent && isEndTimePresent){
            filterPipeline.addStep(new BeforeDateStage(timeFrame.getEndDate()));
            filterPipeline.addStep(new AfterDateStage(timeFrame.getStartDate()));
        } else if (isStartTimePresent){
            filterPipeline.addStep(new AfterDateStage(timeFrame.getStartDate()));
        } else if (isEndTimePresent) {
            filterPipeline.addStep(new BeforeDateStage(timeFrame.getEndDate()));
        }

        // stage correlated to location
        locationStage.ifPresent(filterPipeline::addStep);

        return filterPipeline;
    }
}
