package Tuple;

import JSON.TemperatureDataDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelMapper {

    public List<Pair> mapDTOsToTuples(List<TemperatureDataDTO> DTOs){
        List<Pair> tuples = new ArrayList<>();
        DTOs.forEach(dto -> tuples.addAll(mapDTOToTuples(dto)));
        return tuples;
    }

    public List<Pair> mapDTOToTuples(TemperatureDataDTO dto){
        Map<String, Double> valuesByLocation = dto.getTemperatureByLocation();

        /* Remove locations with no data registered */
        filterOutNullValues(valuesByLocation);

        return createTuples(dto.getDatetime(), valuesByLocation);
    }

    private void filterOutNullValues(Map<String, Double> valuesByLocation)
    {
        valuesByLocation.keySet().removeIf(key -> valuesByLocation.get(key) == null);
    }

    private List<Pair> createTuples(LocalDateTime dateTime, Map<String, Double> valuesByLocation){
        List<Pair> tuples = new ArrayList<>();
        valuesByLocation.forEach(
                (place, value) -> tuples.add(
                new Pair(new Key(dateTime, place), value)
        ));
        return tuples;
    }
}
