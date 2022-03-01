package repository.restriction;

import config.download.DownloadConfig;

import java.util.HashMap;
import java.util.Map;


public class RestrictionChecker {
    private static final RestrictionChecker instance = new RestrictionChecker();

    private final DownloadInfoRepository downloadInfoRepository = DownloadInfoRepository.create(DownloadConfig.instance);
    private final Map<String, RestrictionAtIp> values = new HashMap<>();


}
