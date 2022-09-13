package aggregation.functions;

import JThrustRTC.*;
import model.Pair;

import java.util.List;

public class SumFunctionGPU implements AggregationStrategy{
    @Override
    public double aggregate(List<Pair> filteredData) {
        double[] dataValues = filteredData.stream()
                .mapToDouble(Pair::getValue)
                .toArray();

        DVVector vec = new DVVector(dataValues);
        return (double) TRTC.Reduce(vec, new DVDouble(0), new Functor("Plus"));
    }
}
