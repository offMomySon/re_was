package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.ranges.Range;

import java.time.temporal.ValueRange;

@Slf4j
@ToString
@Getter
public class DownloadRate {
    private static final ValueRange VALID_PERIOD_RANGE = ValueRange.of(1000, 1000000);
    private static final ValueRange VALID_COUNT_RANGE = ValueRange.of(1, 10);

    private final int period;
    private final int count;

    @JsonCreator
    private DownloadRate(@JsonProperty("period") int period,
                         @JsonProperty("count") int count) {
        this.period = validatePeriod(period);
        this.count = validateCount(count);
    }

    private int validatePeriod(int period) {
        if (!VALID_PERIOD_RANGE.isValidIntValue(period)) {
            throw new RuntimeException("period 가 범위내에 존재하지 않습니다.");
        }
        return period;
    }

    private int validateCount(int count) {
        if (!VALID_COUNT_RANGE.isValidIntValue(count)) {
            throw new RuntimeException("count 가 범위내에 존재하지 않습니다.");
        }
        return count;
    }
}
