package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@Getter
public class DownloadConfig {
    private static final String path = "src/main/resources/config/download.json";

    private final String downloadPath;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;
    private final Set<DownloadInfoAtIp> downloadInfoAtIps;

    @JsonCreator
    public DownloadConfig(@NonNull @JsonProperty("downloadPath") String downloadPath,
                          @JsonProperty("downloadRate") DownloadRate downloadRate,
                          @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension,
                          @JsonProperty("downloadInfoAtIps") Set<DownloadInfoAtIp> downloadInfoAtIps) {
        this.downloadPath = downloadPath;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;
        this.downloadInfoAtIps = downloadInfoAtIps;
    }

    public static DownloadConfig create() {
        return ConfigCreator.from(DownloadConfig.class, path);
    }
}
