package aggregation.filters;

import model.Pair;

import java.util.List;

public class NoOperationStep implements Step<List<Pair>, List<Pair>>{
    @Override
    public List<Pair> process(List<Pair> input) {
        return input;
    }
}
