package aggregation.filters;

/**
 * Pipeline for creating SQL-like queries, where each stage of the pipeline
 * filters out input based on location, timeframe.
 * @param <I> input
 * @param <O> output
 */
public class FilterPipeline<I, O> {

    private final Stage<I, O> currentStage;

    public FilterPipeline(Stage<I, O> currentStage) {
        this.currentStage = currentStage;
    }

    // lazy evaluation - pipeline is being constructed beforehand, but each stage is being executed
    // when calling filter() method -> (nextStage uses as an input what currentStage returns as an output)
    public <K> FilterPipeline<I, K> addStep(Stage<O, K> nextStage) {
        return new FilterPipeline<>(input -> nextStage.process(currentStage.process(input)));
    }

    public O filter(I input) {
        return currentStage.process(input);
    }
}
