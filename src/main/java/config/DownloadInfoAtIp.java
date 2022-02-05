package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@ToString
@Getter
public class DownloadInfoAtIp {
    private final String ip;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;

    @JsonCreator
    private DownloadInfoAtIp(@JsonProperty("ip") @NonNull String ip,
                             @JsonProperty("downloadRate") DownloadRate downloadRate,
                             @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension) {
        this.ip = ip;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;
    }
}
