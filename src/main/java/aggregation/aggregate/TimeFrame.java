package aggregation.aggregate;

import java.time.LocalDateTime;

/**
 * Immutable class representing timeframe.
 */
public class TimeFrame {
    // contains LocalDateTime.MIN when startDate doesn't matter
    private final LocalDateTime startDate;
    // contains LocalDateTime.MAX when endDate doesn't matter
    private final LocalDateTime endDate;

    private TimeFrame(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Static method instead of public constructor in order to maintain logic.
     * Assuming that startDate < endDate.
     * @param startDate earlier Date
     * @param endDate later Date
     */
    public static TimeFrame createDateRange(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }

        return new TimeFrame(startDate, endDate);
    }

    /**
     * Method used to check if date is in timeFrame.
     * In program used in order to check whether Aggregate is finished.
     * @param date Timestamp of a data from stream.
     */
    public boolean isDateInRange(LocalDateTime date){
        return (date.isAfter(startDate) && date.isBefore(endDate));
    }
}
