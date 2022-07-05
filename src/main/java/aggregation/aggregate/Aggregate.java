package aggregation.aggregate;

import aggregation.filters.FilterPipeline;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;

public class Aggregate {

    private double calculatedValue;
    private boolean isCompleted;
    private final FilterPipeline<List<Pair>, List<Pair>> pipeline;
    private final AggregateFunction arithmeticMethod;
    private final DateRange datePeriod;

    public Aggregate(FilterPipeline<List<Pair>, List<Pair>> pipeline,
                     AggregateFunction arithmeticMethod,
                     DateRange datePeriod) {
        this.calculatedValue = 0.0f;
        this.isCompleted = false;
        this.pipeline = pipeline;
        this.arithmeticMethod = arithmeticMethod;
        this.datePeriod = datePeriod;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void updateAggregate(List<Pair> data, LocalDateTime timeStamp){
        if(isCompleted) return;

        evaluateCompletion(timeStamp);
        aggregate(pipeline.filter(data));
    }

    public void aggregate(List<Pair> filteredData){
        this.calculatedValue += arithmeticMethod.calculateValue(filteredData);
    }

    public void evaluateCompletion(LocalDateTime timeStamp){
        boolean hasBeenCompleted = !datePeriod.isDateInRange(timeStamp);
        if(hasBeenCompleted) this.isCompleted = true;
    }
}