import aggregation.AggregationEngine;
import aggregation.aggregate.Aggregate;
import aggregation.factories.AggregateFactory;
import aggregation.factories.TimeSpanFactory;
import aggregation.filters.LocationStage;
import aggregation.functions.AggregateFunction;
import aggregation.functions.CountFunction;
import aggregation.functions.SumFunction;
import com.fasterxml.jackson.databind.ObjectMapper;
import fetchdata.DataDeserializer;
import fetchdata.DataReceiver;
import fetchdata.DeserializeJSON;
import model.TupleMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args){

        File inputFile = new File(Main.class.getClassLoader().getResource("temperature.json").getFile());

        DataDeserializer jsonDeserializer = new DataDeserializer(new DeserializeJSON(new ObjectMapper()));
        TupleMapper mapper = new TupleMapper();
        AggregationEngine engine = new AggregationEngine();
        DataReceiver receiver = new DataReceiver(mapper, jsonDeserializer, engine);

        AggregateFactory aggregateFactory = new AggregateFactory();
        TimeSpanFactory timeSpanFactory = new TimeSpanFactory();
        Aggregate sumAggregateAtlanta = aggregateFactory.createAggregate(
                timeSpanFactory.createDateRangeInPeriod(
                        // 2012-10-01 23:00:00
                        LocalDateTime.of(2012, 10, 1, 23, 0),
                        LocalDateTime.of(2012, 10, 2, 1, 0)
                ),
                new LocationStage("Atlanta"),
                new AggregateFunction(new CountFunction())
        );

        engine.addAggregate(sumAggregateAtlanta);

        try {
            receiver.fetchData(inputFile);
        } catch (IOException e) {
            System.out.println("There has been a problem fetching data from file.");
            System.out.println(e.getMessage());
            return;
        }
        //System.out.println(tuples.size()); // 1621078
        engine.aggregatesList.forEach(aggregate -> System.out.println(aggregate.getCalculatedValue()));
    }
}
