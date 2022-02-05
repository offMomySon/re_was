package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadConfig {
    private static final String path = "src/main/resources/config/thread.json";

    private final int usableThreadCount;
    private final int waitableThreadCount;

    @JsonCreator
    private ThreadConfig(@JsonProperty("usableThreadCount") int usableThreadCount,
                         @JsonProperty("waitableThreadCount") int waitableThreadCount) {
        this.usableThreadCount = usableThreadCount;
        this.waitableThreadCount = waitableThreadCount;
    }

    public static ThreadConfig create() {
        return ConfigCreator.from(ThreadConfig.class, path);
    }

}
