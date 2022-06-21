package aggregation;

import aggregation.filters.FilterPipeline;
import aggregation.functions.AggregateFunction;
import model.Pair;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Aggregate {
    private LocalDateTime timeStamp;
    private final FilterPipeline<List<Pair>, List<Pair>> pipeline;
    private final AggregateFunction function;

    public Aggregate(FilterPipeline<List<Pair>, List<Pair>> pipeline, AggregateFunction function) {
        timeStamp = LocalDateTime.now();
        this.pipeline = pipeline;
        this.function = function;
    }
}
