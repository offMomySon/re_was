package repository.restriction;


import config.download.DownloadConfig;
import config.download.DownloadInfoAtIp;
import lombok.NonNull;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

// 책임 - download info 에 대한 빠른 검색
// 컨셉 - download info
public class DownloadInfoRepository {
    private static final String commonIp = "*";

    private final Map<String, DownloadInfoAtIp> values;
    private final DownloadInfoAtIp commonDownloadInfo;

    private DownloadInfoRepository(@NonNull Map<String, DownloadInfoAtIp> values, @NonNull DownloadInfoAtIp commonDownloadInfo) {
        this.values = Collections.unmodifiableMap(values);
        this.commonDownloadInfo = commonDownloadInfo;
    }

    public static DownloadInfoRepository create(@NonNull DownloadConfig downloadConfig) {
        Set<DownloadInfoAtIp> downloadInfoAtIps = downloadConfig.getDownloadInfoAtIps();

        Map<String, DownloadInfoAtIp> newValue = downloadInfoAtIps.stream().collect(Collectors.toMap(
                DownloadInfoAtIp::getIp, Function.identity(), (it1, it2) -> it1
        ));

        return new DownloadInfoRepository(newValue,
                new DownloadInfoAtIp(commonIp, downloadConfig.getDownloadRate(), downloadConfig.getRestrictedFileExtension()));
    }

    public DownloadInfoAtIp find(String ip) {
        return values.getOrDefault(ip, commonDownloadInfo);
    }
}
