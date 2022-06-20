package aggregation;

import model.Pair;

import java.util.List;

public interface Observable {
    void set (Observer o);
    void notify (Observer o, List<Pair> data);
}
