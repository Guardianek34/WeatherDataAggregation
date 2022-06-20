package fetchdata;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DeserializeStrategy {
    List<TemperatureDataDTO> deserialize(File inputFile) throws IOException;
}
