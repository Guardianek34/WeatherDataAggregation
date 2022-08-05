package fetchdata;

import aggregation.Observable;
import aggregation.Observer;
import model.Pair;
import model.TupleMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Concrete class responsible for passing new data from stream to aggregationEngine.
 */
public class DataReceiver implements Observable {

    private final TupleMapper mapper;
    private final DataDeserializer deserializer;
    private Observer aggregationEngine;

    public DataReceiver(TupleMapper mapper,
                        DataDeserializer deserializer,
                        Observer aggregationEngine) {
        this.mapper = mapper;
        this.deserializer = deserializer;
        this.aggregationEngine = aggregationEngine;
    }

    public void fetchData(File inputFile) throws IOException {
        List<TemperatureDataDTO> weatherData = deserializer.deserializeData(inputFile);
        List<Pair> mappedData = mapper.mapDTOsToTuples(weatherData);
        /*
            aggregationEngine gets notified with a timeStamp and data (in final version time should
            be extracted from one of the entries)
            - assuming that all the entries coming in "single package" got same timestamp
            - not supporting timeframe
         */
        notify(aggregationEngine, mappedData, LocalDateTime.now());
    }

    @Override
    public void set(Observer o) {
        this.aggregationEngine = o;
    }

    @Override
    public void notify(Observer o, List<Pair> data, LocalDateTime dataTimeStamp) {
        this.aggregationEngine.update(data, dataTimeStamp);
    }
}
