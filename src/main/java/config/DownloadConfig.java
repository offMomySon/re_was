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
@Getter
public class DownloadConfig {
    private static final String path = "src/main/resources/config/download.json";

    public static final DownloadConfig instance = create();

    private final Path root;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;
    private final Set<DownloadInfoAtIp> downloadInfoAtIps;

    private DownloadConfig(@NonNull Path root,
                           DownloadRate downloadRate,
                           Set<String> restrictedFileExtension,
                           Set<DownloadInfoAtIp> downloadInfoAtIps) {
        this.root = root;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;
        this.downloadInfoAtIps = downloadInfoAtIps;

        log.info("root = {}", root);
        log.info("downloadRate = {}", downloadRate);
        log.info("restrictedFileExtension = {}", restrictedFileExtension);
        log.info("downloadInfoAtIps = {}", downloadInfoAtIps);
    }

    @JsonCreator
    private static DownloadConfig ofJackSon(@NonNull @JsonProperty("root") String root,
                                            @JsonProperty("downloadRate") DownloadRate downloadRate,
                                            @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension,
                                            @JsonProperty("downloadInfoAtIps") Set<DownloadInfoAtIp> downloadInfoAtIps) {
        Path path = PathUtil.normalizePath(Paths.get(root));

        return new DownloadConfig(path, downloadRate, restrictedFileExtension, downloadInfoAtIps);
    }

    private static DownloadConfig create() {
        return new Config<>(DownloadConfig.class, path).create();
    }
}
