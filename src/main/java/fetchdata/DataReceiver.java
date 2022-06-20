package fetchdata;

import aggregation.Observable;
import aggregation.Observer;
import model.Pair;
import model.TupleMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataReceiver implements Observable {

    private TupleMapper mapper;
    private DataDeserializer deserializer;
    private Observer aggregationEngine;

    public DataReceiver(TupleMapper mapper, DataDeserializer deserializer, Observer aggregationEngine) {
        this.mapper = mapper;
        this.deserializer = deserializer;
        this.aggregationEngine = aggregationEngine;
    }

    public void fetchData(File inputFile) throws IOException {
        List<TemperatureDataDTO> weatherData = deserializer.deserializeData(inputFile);
        List<Pair> mappedData = mapper.mapDTOsToTuples(weatherData);
        notify(aggregationEngine, mappedData);
    }

    @Override
    public void set(Observer o) {
        this.aggregationEngine = o;
    }

    @Override
    public void notify(Observer o, List<Pair> data) {
        this.aggregationEngine.update(data);
    }
}
