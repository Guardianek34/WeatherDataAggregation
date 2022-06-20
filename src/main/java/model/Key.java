package model;

import java.time.LocalDateTime;

public final class Key {

    private final LocalDateTime dateTime;
    private final String cityName;

    public Key(LocalDateTime dateTime, String cityName) {
        this.dateTime = dateTime;
        this.cityName = cityName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return cityName;
    }

    @Override
    public String toString() {
        return "Key{" +
                "dateTime=" + dateTime +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
