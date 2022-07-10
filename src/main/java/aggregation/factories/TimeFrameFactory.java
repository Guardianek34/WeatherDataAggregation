package aggregation.factories;

import aggregation.aggregate.TimeFrame;

import java.time.LocalDateTime;
import java.util.Optional;

public class TimeFrameFactory {

    public TimeFrame createDateRange(Optional<LocalDateTime> startTime,
                                     Optional<LocalDateTime> endTime) {
        boolean isStartTimePresent = startTime.isPresent();
        boolean isEndTimePresent = endTime.isPresent();

        if (isStartTimePresent && isEndTimePresent) {
            return TimeFrame.createDateRange(startTime.get(), endTime.get());
        } else if (isStartTimePresent) {
            return TimeFrame.createDateRange(startTime.get(), LocalDateTime.MAX);
        } else if (isEndTimePresent) {
            return TimeFrame.createDateRange(LocalDateTime.MIN, endTime.get());
        } else {
            return TimeFrame.createDateRange(LocalDateTime.MIN, LocalDateTime.MAX);
        }

    }
}
