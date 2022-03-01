package config.download;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;

@Slf4j
@ToString
@Getter
// 책임 - 데이터 성격.
// 컨셉 - ip 의 설정 정보를 가진다.
public class DownloadInfoAtIp {

    private final String ip;
    private final DownloadRate downloadRate;
    private final Set<String> restrictedFileExtension;

    @JsonCreator
    public DownloadInfoAtIp(@NonNull @JsonProperty("ip") String ip,
                            @JsonProperty("downloadRate") DownloadRate downloadRate,
                            @JsonProperty("restrictedFileExtension") Set<String> restrictedFileExtension) {
        this.ip = ip;
        this.downloadRate = downloadRate;
        this.restrictedFileExtension = restrictedFileExtension;
    }
}
