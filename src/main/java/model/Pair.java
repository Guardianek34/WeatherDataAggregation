package model;

import java.time.LocalDateTime;

public final class Pair {
    private final Key key;
    private final Double value;

    public Pair(Key key, Double value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public LocalDateTime getDateTime() {
        return key.getDateTime();
    }

    public City getCity() {
        return key.getCity();
    }

    public String getCityName() {
        return key.getCity().getCityName();
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key.toString() +
                ", value=" + value +
                '}';
    }
}
