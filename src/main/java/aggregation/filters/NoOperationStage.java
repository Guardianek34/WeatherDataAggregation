package aggregation.filters;

import model.Pair;

import java.util.List;

/**
 * SELECT * FROM exampleInput
 * (input = output)
 */
public class NoOperationStage implements Stage<List<Pair>, List<Pair>> {
    @Override
    public List<Pair> process(List<Pair> input) {
        return input;
    }
}
