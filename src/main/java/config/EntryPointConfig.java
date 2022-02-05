package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntryPointConfig {
    private static final String path = "src/main/resources/config/entry_point.json";

    private final int port;
    private final String welcomePagePath;

    @JsonCreator
    private EntryPointConfig(@JsonProperty("port") int port,
                             @JsonProperty("welcomePagePath") @NonNull String welcomePagePath) {
        this.port = port;
        this.welcomePagePath = welcomePagePath;

        log.info("Port = {}", port);
        log.info("welcomePagePath = {}", welcomePagePath);
    }

    public static EntryPointConfig create() {
        return ConfigCreator.from(EntryPointConfig.class, path);
    }
}
