package aggregation.functions;

import JThrustRTC.DVInt32;
import JThrustRTC.DVVector;
import JThrustRTC.Functor;
import JThrustRTC.TRTC;
import model.Pair;

import java.util.List;

public class SumFunctionGPU implements AggregationStrategy{
    @Override
    public double aggregate(List<Pair> filteredData) {
        double[] dataValues = filteredData.stream()
                .mapToDouble(Pair::getValue)
                .toArray();

        DVVector vec = new DVVector(dataValues);
        return (double) TRTC.Reduce(vec, new DVInt32(0), new Functor("Plus"));
    }
}
