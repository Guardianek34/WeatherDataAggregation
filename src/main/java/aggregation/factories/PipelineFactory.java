package aggregation.factories;

import aggregation.aggregate.TimeSpan;
import aggregation.filters.*;
import model.Pair;

import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PipelineFactory {

    public FilterPipeline<List<Pair>, List<Pair>> createPipelineByLocationTime(
            TimeSpan timeSpan,
            LocationStage locationStage){

        // using LocalDateTime.MIN and MAX to represent whether values are present (instead of null values)
        // look at TimeSpanFactory
        boolean isStartTimePresent = !timeSpan.getStartDate().isEqual(LocalDateTime.MIN);
        boolean isEndTimePresent = !timeSpan.getStartDate().isEqual(LocalDateTime.MAX);
        FilterPipeline<List<Pair>, List<Pair>> filterPipeline = new FilterPipeline<>(locationStage);

        // stages correlated to time
        if(isStartTimePresent && isEndTimePresent){
            filterPipeline.addStep(new BeforeDateStage(timeSpan.getEndDate()));
            filterPipeline.addStep(new AfterDateStage(timeSpan.getStartDate()));
        } else if (isStartTimePresent){
            filterPipeline.addStep(new AfterDateStage(timeSpan.getStartDate()));
        } else if (isEndTimePresent) {
            filterPipeline.addStep(new BeforeDateStage(timeSpan.getEndDate()));
        }

        return filterPipeline;
    }
}
