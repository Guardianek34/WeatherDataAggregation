import aggregation.AggregationEngine;
import aggregation.aggregate.Aggregate;
import aggregation.factories.AggregateFactory;
import aggregation.factories.TimeSpanFactory;
import aggregation.filters.LocationStage;
import aggregation.filters.NoOperationStage;
import aggregation.functions.AggregateFunction;
import aggregation.functions.CountFunction;
import aggregation.functions.SumFunction;
import aggregation.functions.SumFunctionGPU;
import com.fasterxml.jackson.databind.ObjectMapper;
import fetchdata.DataDeserializer;
import fetchdata.DataReceiver;
import fetchdata.DeserializeJSON;
import jcuda.Pointer;
import jcuda.runtime.JCuda;
import model.TupleMapper;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

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

        Aggregate countAtlanta = aggregateFactory.createSumAggregate(
                timeSpanFactory.createInfiniteTimeFrame(),
                new LocationStage("Atlanta"),
                new AggregateFunction(new CountFunction())
        );

        engine.addAggregate(countAtlanta);

        Aggregate sumAllCities = aggregateFactory.createSumAggregate(
                timeSpanFactory.createInfiniteTimeFrame(),
                new AggregateFunction(new SumFunction())
        );

        engine.addAggregate(sumAllCities);

        Aggregate averageDallasFrom2012To2013 = aggregateFactory.createAverageAggregate(
                timeSpanFactory.createDateRangeInPeriod(
                        LocalDateTime.of(2012, 1, 1, 0, 0),
                        LocalDateTime.of(2013, 1, 1, 0, 0)),
                new LocationStage("Dallas")
        );

        engine.addAggregate(averageDallasFrom2012To2013);

        Aggregate sumInVancouverBeforeSpecificDate = aggregateFactory.createSumAggregate(
                timeSpanFactory.createDateRangeBefore(
                        LocalDateTime.of(2012, 10, 1, 18, 0)),
                new LocationStage("Vancouver"),
                new AggregateFunction(new SumFunctionGPU())
        );

        engine.addAggregate(sumInVancouverBeforeSpecificDate);

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
