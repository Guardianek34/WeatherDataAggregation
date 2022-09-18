package aggregation;

import aggregation.aggregate.Aggregate;
import model.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Most important class in our program - responsible for aggregating all aggregates.
 * Also stores newly read data.
 */
public class AggregationEngine implements Observer {

    /**
     * New Data
     */
    public List<Pair> data = new ArrayList<>();


    public List<Aggregate> aggregatesList = new ArrayList<>();


    @Override
    public void update(List<Pair> data, LocalDateTime timeStamp) {
        this.data = data;
        this.aggregateTuples(timeStamp); // after receiving data - aggregate
    }

    public void addAggregate(Aggregate aggregate) {
        this.aggregatesList.add(aggregate);
    }

    /**
     * Filtering out completed aggregates
     * (assuming data that comes from external API is fresh)
     *
     * @param timeStamp timeStamp of a fresh data
     */
    public void aggregateTuples(LocalDateTime timeStamp) {
        this.aggregatesList.stream() // or parallelStream
                .filter(a -> !a.isCompleted()) // never considering finished aggregates - they are read-only
                .forEach(a -> a.updateAggregate(data, timeStamp));
    }
}
