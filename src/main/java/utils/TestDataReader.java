package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestDataReader {
    public static Map<String, String> getTestData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {});
    }
}