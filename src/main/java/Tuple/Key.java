package Tuple;

import java.time.LocalDateTime;

public final class Key {

    private final LocalDateTime dateTime;
    private final String location;

    public Key(LocalDateTime dateTime, String location) {
        this.dateTime = dateTime;
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Key{" +
                "dateTime=" + dateTime +
                ", location='" + location + '\'' +
                '}';
    }
}
