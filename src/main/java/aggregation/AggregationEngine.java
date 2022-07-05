package aggregation;

import aggregation.aggregate.Aggregate;
import model.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AggregationEngine implements Observer{

    public List<Pair> tuplesToAggregate = new ArrayList<>();
    public List<Aggregate> aggregatesList = new ArrayList<>();

    @Override
    public void update(List<Pair> data, LocalDateTime timeStamp) {
        this.tuplesToAggregate = data;
        this.aggregateTuples(timeStamp);
    }

    public void aggregateTuples(LocalDateTime timeStamp)
    {
        aggregatesList.stream()
                .filter(a -> !a.isCompleted())
                .forEach(a -> a.updateAggregate(tuplesToAggregate, timeStamp));
    }
}
