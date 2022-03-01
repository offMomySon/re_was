package config.download;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import config.RootPath;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.Util;

import java.util.Set;

@Slf4j
@Getter
// TODO
// 책임 - 데이터 성격
// 컨셉 - 다운로드 설정에 대한 주기, 제한들에 대한 데이터 정보를 가진다..
public class DownloadConfig {
    private static final String path = "src/main/resources/config/download.json";

    public static final DownloadConfig instance = create();

    private final RootPath rootPath;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;
    private final Set<DownloadInfoAtIp> downloadInfoAtIps;

    @JsonCreator
    private DownloadConfig(@NonNull @JsonProperty("root") RootPath rootPath,
                           @JsonProperty("downloadRate") DownloadRate downloadRate,
                           @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension,
                           @JsonProperty("downloadInfoAtIps") Set<DownloadInfoAtIp> downloadInfoAtIps) {
        this.rootPath = rootPath;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;
        this.downloadInfoAtIps = downloadInfoAtIps;

        log.info("rootPath = {}", rootPath);
        log.info("downloadRate = {}", downloadRate);
        log.info("restrictedFileExtension = {}", restrictedFileExtension);
        log.info("downloadInfoAtIps = {}", downloadInfoAtIps);
    }

    private static DownloadConfig create() {
        return Util.createConfig(path, DownloadConfig.class);
    }
}
