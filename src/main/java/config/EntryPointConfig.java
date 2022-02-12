package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.PathUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ValueRange;

@Slf4j
@Getter
public class EntryPointConfig {
    private static final String path = "src/main/resources/config/entry_point.json";
    private static final ValueRange VALID_PORT_RANGE = ValueRange.of(4000, 10000);

    public static final EntryPointConfig instance = create();

    private final int port;
    private final Path welcomePage;

    private EntryPointConfig(int port, @NonNull Path welcomePage) {
        this.port = validatePort(port);
        this.welcomePage = welcomePage;

        log.info("Port = {}", port);
        log.info("welcomePagePath = {}", welcomePage);
    }

    @JsonCreator
    private static EntryPointConfig ofJackson(@JsonProperty("port") int port,
                                              @JsonProperty("welcomePagePath") @NonNull String welcomePagePath) {
        Path path = PathUtil.normalizePath(Paths.get(welcomePagePath));
        return new EntryPointConfig(port, path);
    }

    private int validatePort(int port) {
        if (!VALID_PORT_RANGE.isValidIntValue(port)) {
            throw new RuntimeException("port 가 유효범위내에 존재하지 않습니다.");
        }
        return port;
    }

    private static EntryPointConfig create() {
        return new Config<>(EntryPointConfig.class, path).create();
    }
}
