package fetchdata;

import aggregation.Observable;
import aggregation.Observer;
import model.Pair;
import model.TupleMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class DataReceiver implements Observable {

    private final TupleMapper mapper;
    private final DataDeserializer deserializer;
    private Observer aggregationEngine;

    public DataReceiver(TupleMapper mapper, DataDeserializer deserializer, Observer aggregationEngine) {
        this.mapper = mapper;
        this.deserializer = deserializer;
        this.aggregationEngine = aggregationEngine;
    }

    public void fetchData(File inputFile) throws IOException {
        List<TemperatureDataDTO> weatherData = deserializer.deserializeData(inputFile);
        List<Pair> mappedData = mapper.mapDTOsToTuples(weatherData);
        notify(aggregationEngine, mappedData, LocalDateTime.now());
    }

    @Override
    public void set(Observer o) {
        this.aggregationEngine = o;
    }

    @Override
    public void notify(Observer o, List<Pair> data, LocalDateTime timeStamp) {
        this.aggregationEngine.update(data, timeStamp);
    }
}
