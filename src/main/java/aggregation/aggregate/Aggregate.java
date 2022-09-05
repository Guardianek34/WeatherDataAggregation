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
public abstract class Aggregate {
    protected double calculatedValue;
    private boolean isCompleted;
    private final FilterPipeline<List<Pair>, List<Pair>> pipeline;
    private final AggregateFunction arithmeticMethod;
    private final TimeSpan timeSpan;


    public Aggregate(FilterPipeline<List<Pair>, List<Pair>> pipeline,
                     AggregateFunction arithmeticMethod,
                     TimeSpan timeSpan) {
        this.calculatedValue = 0.0f;
        this.isCompleted = false;
        this.pipeline = pipeline;
        this.arithmeticMethod = arithmeticMethod;
        this.timeSpan = timeSpan;
    }

    public void updateAggregate(List<Pair> data, LocalDateTime dataTimeStamp){
        boolean hasBeenFinished = isAggregateFinished(dataTimeStamp);
        // when the flag is set true, aggregate becomes read-only
        // and is never updated again
        if(hasBeenFinished){
            this.isCompleted = true;
        }

        aggregate(pipeline.filter(data));
    }

    public abstract void aggregate(List<Pair> filteredData);

    public Double calculateNewValue(List<Pair> filteredData){
        return arithmeticMethod.calculateValue(filteredData);
    }

    public boolean isAggregateFinished(LocalDateTime dataTimeStamp){
        // aggregate is judged as completed when data timeStamp is not in timeFrame
        // (future data won't affect the result of aggregation assuming it will be newer)
        return !timeSpan.isDateInRange(dataTimeStamp);
    }


    public double getCalculatedValue() {
        return calculatedValue;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}