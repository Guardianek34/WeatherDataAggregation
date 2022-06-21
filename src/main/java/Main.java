import aggregation.AggregationEngine;
import aggregation.filters.AfterDateStep;
import aggregation.filters.BeforeDateStep;
import aggregation.filters.FilterPipeline;
import aggregation.filters.LocationStep;
import com.fasterxml.jackson.databind.ObjectMapper;
import fetchdata.DataDeserializer;
import fetchdata.DataReceiver;
import fetchdata.DeserializeJSON;
import model.TupleMapper;
import model.Pair;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        File inputFile = new File(Main.class.getClassLoader().getResource("temperature.json").getFile());

        DataDeserializer jsonDeserializer = new DataDeserializer(new DeserializeJSON(new ObjectMapper()));
        TupleMapper mapper = new TupleMapper();
        AggregationEngine engine = new AggregationEngine();
        DataReceiver receiver = new DataReceiver(mapper, jsonDeserializer, engine);
        receiver.set(engine);

        try {
            receiver.fetchData(inputFile);
        } catch (IOException e) {
            System.out.println("There has been a problem fetching data from file.");
            System.out.println(e.getMessage());
            return;
        }
        //System.out.println(tuples.size()); // 1621078
    }
}
