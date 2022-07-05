package aggregation;

import model.Pair;

import java.time.LocalDateTime;
import java.util.List;

public interface Observer {
    void update(List<Pair> data, LocalDateTime timeStamp);
}
