package aggregation.filters;

public class FilterPipeline<I, O> {

    private final Stage<I, O> currentStage;

    public FilterPipeline(Stage<I, O> currentStage) {
        this.currentStage = currentStage;
    }

    public <K> FilterPipeline<I, K> addStep(Stage<O, K> next) {
        return new FilterPipeline<>(input -> next.process(currentStage.process(input)));
    }

    public O filter(I input) {
        return currentStage.process(input);
    }
}
