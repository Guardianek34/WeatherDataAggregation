package fetchdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeserializeJSON implements DeserializeStrategy {

    private final ObjectMapper objectMapper;

    public DeserializeJSON(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<TemperatureDataDTO> deserialize(File inputFile) throws IOException {
        return objectMapper.readValue(
                inputFile,
                new TypeReference<>(){}
        );
    }
}
