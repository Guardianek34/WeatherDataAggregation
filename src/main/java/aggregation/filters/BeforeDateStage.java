package aggregation.filters;

import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * WHERE Date < "exampleDate"
 */
public class BeforeDateStage implements Stage<List<Pair>, List<Pair>> {

    private final LocalDateTime dateTime;

    public BeforeDateStage(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public List<Pair> process(List<Pair> input) {
        return input.stream()
                .filter(p -> p.getDateTime().isBefore(dateTime))
                .collect(Collectors.toList());
    }
}
