package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Getter
public class DownloadRate {
    private final int period;
    private final int count;

    @JsonCreator
    private DownloadRate(@JsonProperty("period") int period,
                         @JsonProperty("count") int count) {
        this.period = validatePeriod(period);
        this.count = validateCount(count);
    }

    private int validatePeriod(int period) {
        if (period < 1000) {
            throw new RuntimeException("period 가 1000 ms 이상이 되야 합니다.");
        }
        if (period > 1000000) {
            throw new RuntimeException("period 가 1000000 ms 이하가 되야 합니다.");
        }
        return period;
    }

    private int validateCount(int count) {
        if (count < 1) {
            throw new RuntimeException("count 가 1 이상이 되야 합니다.");
        }
        if (count > 10) {
            throw new RuntimeException("count 가 10 이하가 되야 합니다.");
        }
        return count;
    }
}
