package fetchdata;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TemperatureDataDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;
    private Map<String, Double> temperatureByLocation = new HashMap<>();


    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }


    @JsonAnyGetter
    public Map<String, Double> getTemperatureByLocation() {
        return temperatureByLocation;
    }

    public void setTemperatureByLocation(Map<String, Double> temperatureByLocation) {
        this.temperatureByLocation = temperatureByLocation;
    }

    @JsonAnySetter
    public void addTemperatureByLocation(String location, Double temperature) {
        this.temperatureByLocation.put(location, temperature);
    }

    @Override
    public String toString() {
        return "TemperatureDataDTO{" +
                "datetime=" + datetime +
                ", temperatureByLocation=" + temperatureByLocation +
                '}';
    }
}
