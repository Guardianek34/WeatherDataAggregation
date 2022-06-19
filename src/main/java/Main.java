import JSON.DataDeserializer;
import JSON.DeserializeJSON;
import JSON.TemperatureDataDTO;
import Tuple.ModelMapper;
import Tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File inputFile = new File(Main.class.getClassLoader().getResource("temperature.json").getFile());
        DataDeserializer jsonDeserializer = new DataDeserializer(new DeserializeJSON());
        List<TemperatureDataDTO> data;
        try {
            data = jsonDeserializer.fetchData(inputFile);
        } catch (IOException e) {
            System.out.println("There has been a problem fetching data from file.");
            System.out.println(e.getMessage());
            return;
        }

        ModelMapper mapper = new ModelMapper();
        List<Pair> tuples = mapper.mapDTOsToTuples(data);
        System.out.println(tuples.size()); // 1621078
    }
}
