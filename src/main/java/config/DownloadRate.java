package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadRate {
    private final int period;
    private final int count;

    @JsonCreator
    public DownloadRate(@JsonProperty("period") int period,
                        @JsonProperty("count") int count) {
        this.period = period;
        this.count = count;

        log.info("period = {}", period);
        log.info("count = {}", count);
    }
}
