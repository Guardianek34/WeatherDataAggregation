package aggregation.factories;

import aggregation.aggregate.TimeSpan;
import aggregation.filters.*;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PipelineFactory {

    public FilterPipeline<List<Pair>, List<Pair>> createPipelineByLocationTime(
            TimeSpan timeSpan,
            LocationStage locationStage) {

        // using LocalDateTime.MIN and MAX to represent whether values are present (instead of null values)
        // look at TimeSpanFactory
        boolean isStartTimePresent = !timeSpan.getStartDate().isEqual(LocalDateTime.MIN);
        boolean isEndTimePresent = !timeSpan.getEndDate().isEqual(LocalDateTime.MAX);

        FilterPipeline<List<Pair>, List<Pair>> filterPipeline =
                new FilterPipeline<>(Objects.requireNonNullElseGet(locationStage, NoOperationStage::new));

        // stages correlated to time
        if (isStartTimePresent && isEndTimePresent) {
            return filterPipeline
                    .addStep(new BeforeDateStage(timeSpan.getEndDate()))
                    .addStep(new AfterDateStage(timeSpan.getStartDate()));
        } else if (isStartTimePresent) {
            return filterPipeline.addStep(new AfterDateStage(timeSpan.getStartDate()));
        } else if (isEndTimePresent) {
            return filterPipeline.addStep(new BeforeDateStage(timeSpan.getEndDate()));
        }

        return filterPipeline;
    }
}
