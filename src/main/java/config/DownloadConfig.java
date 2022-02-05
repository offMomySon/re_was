package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.PathUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Slf4j
public class DownloadConfig {
    private static final String path = "src/main/resources/config/download.json";

    private final Path downloadPath;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;
    private final Set<DownloadInfoAtIp> downloadInfoAtIps;

    private DownloadConfig(@NonNull Path downloadPath,
                           DownloadRate downloadRate,
                           Set<String> restrictedFileExtension,
                           Set<DownloadInfoAtIp> downloadInfoAtIps) {
        this.downloadPath = downloadPath;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;
        this.downloadInfoAtIps = downloadInfoAtIps;

        log.info("downloadPath = {}", downloadPath);
        log.info("downloadRate = {}", downloadRate);
        log.info("restrictedFileExtension = {}", restrictedFileExtension);
        log.info("downloadInfoAtIps = {}", downloadInfoAtIps);
    }

    @JsonCreator
    private static DownloadConfig ofJackSon(@NonNull @JsonProperty("downloadPath") String downloadPath,
                                            @JsonProperty("downloadRate") DownloadRate downloadRate,
                                            @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension,
                                            @JsonProperty("downloadInfoAtIps") Set<DownloadInfoAtIp> downloadInfoAtIps) {
        Path path = PathUtil.removeRelativePath(Paths.get(downloadPath));

        return new DownloadConfig(path, downloadRate, restrictedFileExtension, downloadInfoAtIps);
    }

    public static DownloadConfig create() {
        return ConfigCreator.from(DownloadConfig.class, path);
    }
}
