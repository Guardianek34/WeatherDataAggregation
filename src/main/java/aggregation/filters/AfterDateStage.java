package aggregation.filters;

import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AfterDateStage implements Stage<List<Pair>, List<Pair>> {

    private final LocalDateTime dateTime;

    public AfterDateStage(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public List<Pair> process(List<Pair> input) {
        return input.stream()
                .filter(p -> p.getDateTime().isAfter(dateTime))
                .collect(Collectors.toList());
    }
}
