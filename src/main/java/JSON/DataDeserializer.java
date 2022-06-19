package JSON;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataDeserializer {

    private final DeserializeStrategy deserializationMethod;

    public DataDeserializer(DeserializeStrategy deserializationMethod) {
        this.deserializationMethod = deserializationMethod;
    }

    public List<TemperatureDataDTO> fetchData(File inputFile) throws IOException {
        return deserializationMethod.deserialize(inputFile);
    }
}
