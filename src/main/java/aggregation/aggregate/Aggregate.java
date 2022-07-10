package aggregation.aggregate;

import aggregation.filters.FilterPipeline;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Object representing aggregate - containing all the information
 * required for aggregation.
 */
public class Aggregate {
    private double calculatedValue;
    private boolean isCompleted;
    private final FilterPipeline<List<Pair>, List<Pair>> pipeline;
    private final AggregateFunction arithmeticMethod;
    private final TimeFrame timeFrame;

    public Aggregate(FilterPipeline<List<Pair>, List<Pair>> pipeline,
                     AggregateFunction arithmeticMethod,
                     TimeFrame timeFrame) {
        this.calculatedValue = 0.0f;
        this.isCompleted = false;
        this.pipeline = pipeline;
        this.arithmeticMethod = arithmeticMethod;
        this.timeFrame = timeFrame;
    }

    public double getCalculatedValue() {
        return calculatedValue;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void updateAggregate(List<Pair> data, LocalDateTime dataTimeStamp){

        boolean hasBeenFinished = evaluateCompletion(dataTimeStamp);

        if(hasBeenFinished){
            // when the flag is set true, aggregate becomes read-only
            // and is never updated again
            this.isCompleted = true;
        }
        else aggregate(pipeline.filter(data));
    }

    public void aggregate(List<Pair> filteredData){
        // currently, supports only addition methods
        this.calculatedValue += arithmeticMethod.calculateValue(filteredData);
    }

    public boolean evaluateCompletion(LocalDateTime dataTimeStamp){
        // aggregate is judged as completed when data timeStamp is not in timeFrame
        // (future data won't affect the result of aggregation assuming it will be newer)
        return !timeFrame.isDateInRange(dataTimeStamp);
    }
}