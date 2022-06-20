package aggregation;

import aggregation.filters.AggregateFilter;
import aggregation.functions.AggregateFunction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Aggregate {
    private LocalDateTime timeStamp;
    private List<AggregateFilter> filterList = new ArrayList<>();
    private AggregateFunction function;
}
