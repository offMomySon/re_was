package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class ConfigCreator {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> T from(Class<T> clazz, String path) {
        try {
            return objectMapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
