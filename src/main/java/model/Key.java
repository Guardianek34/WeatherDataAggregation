package model;

import java.time.LocalDateTime;

public final class Key {

    private final LocalDateTime dateTime;
    private final City city;

    public Key(LocalDateTime dateTime, City city) {
        this.dateTime = dateTime;
        this.city = city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Key{" +
                "dateTime=" + dateTime +
                ", cityName='" + city.getCityName() + '\'' +
                '}';
    }
}
