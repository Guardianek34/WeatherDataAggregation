package aggregation.factories;

import aggregation.aggregate.TimeSpan;

import java.time.LocalDateTime;

public class TimeSpanFactory {

    public TimeSpan createDateRangeBefore(LocalDateTime endDate) {
        return TimeSpan.createDateRange(LocalDateTime.MIN, endDate);
    }

    public TimeSpan createDateRangeAfter(LocalDateTime startDate) {
        return TimeSpan.createDateRange(startDate, LocalDateTime.MAX);
    }

    public TimeSpan createDateRangeInPeriod(LocalDateTime startDate, LocalDateTime endTime) {
        return TimeSpan.createDateRange(startDate, endTime);
    }

    public TimeSpan createInfiniteTimeFrame() {
        return TimeSpan.createDateRange(LocalDateTime.MIN, LocalDateTime.MAX);
    }
}
