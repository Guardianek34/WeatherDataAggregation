package aggregation.filters;

public interface Step<I, O> {
    O process(I input);
}
