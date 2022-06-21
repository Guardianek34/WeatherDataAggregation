package aggregation.filters;

public class FilterPipeline<I, O> {

    private final Step<I, O> currentStep;

    public FilterPipeline(Step<I, O> currentStep) {
        this.currentStep = currentStep;
    }

    public <K> FilterPipeline<I, K> addStep(Step<O, K> next) {
        return new FilterPipeline<>(input -> next.process(currentStep.process(input)));
    }

    public O execute(I input) {
        return currentStep.process(input);
    }
}
