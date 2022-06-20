package aggregation;

import model.Pair;

import java.util.List;

public interface Observer {
    void update(List<Pair> data);
}
