package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ThreadConfig {
    private static final String path = "src/main/resources/config/thread.json";

    private static ThreadConfig threadConfig = getInstance();

    private final int usableThreadCount;
    private final int waitableThreadCount;

    @JsonCreator
    private ThreadConfig(@JsonProperty("usableThreadCount") int usableThreadCount,
                         @JsonProperty("waitableThreadCount") int waitableThreadCount) {
        this.usableThreadCount = validateUsableThreadCount(usableThreadCount);
        this.waitableThreadCount = validateWaitableThreadCount(waitableThreadCount);

        log.info("usableThreadCount = {}", usableThreadCount);
        log.info("waitableThreadCount = {}", waitableThreadCount);
    }

    private int validateUsableThreadCount(int usableThreadCount) {
        if (usableThreadCount <= 0) {
            throw new RuntimeException("usableThreadCount 가 1 이상이 되야합니다.");
        }
        if (usableThreadCount > 100) {
            throw new RuntimeException("usableThreadCount 가 100 이하가 되야합니다.");
        }
        return usableThreadCount;
    }

    private int validateWaitableThreadCount(int waitableThreadCount) {
        if (waitableThreadCount <= 0) {
            throw new RuntimeException("waitableThreadCount 가 1 이상 되야합니다.");
        }
        if (waitableThreadCount > 100) {
            throw new RuntimeException("usableThreadCount 가 100 이하가 되야합니다.");
        }
        return waitableThreadCount;
    }

    private static ThreadConfig create() {
        return ConfigCreator.from(ThreadConfig.class, path);
    }

    public static ThreadConfig getInstance() {
        if (threadConfig == null) {
            return threadConfig = create();
        }
        return threadConfig;
    }
}
