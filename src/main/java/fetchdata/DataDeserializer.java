package fetchdata;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataDeserializer {

    private DeserializeStrategy deserializationMethod;

    public DataDeserializer(DeserializeStrategy deserializationMethod) {
        this.deserializationMethod = deserializationMethod;
    }

    public List<TemperatureDataDTO> deserializeData(File inputFile) throws IOException {
        return deserializationMethod.deserialize(inputFile);
    }
}
