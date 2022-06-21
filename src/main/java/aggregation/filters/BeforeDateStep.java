package aggregation.filters;

import model.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BeforeDateStep implements Step<List<Pair>, List<Pair>>{

    private final LocalDateTime dateTime;

    public BeforeDateStep(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public List<Pair> process(List<Pair> input) {
        return input.stream()
                .filter(p -> p.getDateTime().isBefore(dateTime))
                .collect(Collectors.toList());
    }
}
