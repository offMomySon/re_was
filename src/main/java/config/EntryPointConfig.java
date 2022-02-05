package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.IoUtil;
import util.PathUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Getter
public class EntryPointConfig {
    private static final String path = "src/main/resources/config/entry_point.json";

    private static EntryPointConfig entryPointConfig = getInstance();

    private final int port;
    private final Path welcomePagePath;

    private EntryPointConfig(int port, @NonNull Path welcomePagePath) {
        this.port = validatePort(port);
        this.welcomePagePath = welcomePagePath;

        log.info("Port = {}", port);
        log.info("welcomePagePath = {}", welcomePagePath);
    }

    @JsonCreator
    private static EntryPointConfig ofJackson(@JsonProperty("port") int port,
                                              @JsonProperty("welcomePagePath") @NonNull String welcomePagePath) {
        Path path = PathUtil.removeRelativePath(Paths.get(welcomePagePath));
        return new EntryPointConfig(port, path);
    }

    private int validatePort(int port) {
        if (port < 4000) {
            throw new RuntimeException("port 가 4000 이상이 되야 합니다.");
        }
        if (port > 10000) {
            throw new RuntimeException("port 가 10000 이하가 되야 합니다.");
        }
        return port;
    }

    private static EntryPointConfig create() {
        return ConfigCreator.from(EntryPointConfig.class, path);
    }

    public static EntryPointConfig getInstance() {
        if (entryPointConfig == null) {
            return entryPointConfig = create();
        }
        return entryPointConfig;
    }
}
