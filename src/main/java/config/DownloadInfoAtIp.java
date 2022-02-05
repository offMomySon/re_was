package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class DownloadInfoAtIp {
    private final String ip;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;

    @JsonCreator
    public DownloadInfoAtIp(@JsonProperty("ip") @NonNull String ip,
                            @JsonProperty("downloadRate") DownloadRate downloadRate,
                            @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension) {
        this.ip = ip;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;

        log.info("ip = {}", ip);
        log.info("downloadRate = {}", downloadRate);
        log.info("restrictedFileExtension = {}", restrictedFileExtension);
    }
}
