package aggregation;

import model.Pair;

import java.util.ArrayList;
import java.util.List;

public class AggregationEngine implements Observer{

    public List<Pair> tuplesToAggregate = new ArrayList<>();

    @Override
    public void update(List<Pair> data) {
        this.tuplesToAggregate = data;
        this.aggregateTuples();
    }

    public void aggregateTuples()
    {
        throw new UnsupportedOperationException();
    }
}
