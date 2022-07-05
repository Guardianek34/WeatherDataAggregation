package aggregation.aggregate;

import java.time.LocalDateTime;

public class DateRange {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public DateRange(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateRange createDateRange(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }

        return new DateRange(startDate, endDate);
    }

    public boolean isDateInRange(LocalDateTime date){
        return (date.isAfter(startDate) && date.isBefore(endDate));
    }
}
