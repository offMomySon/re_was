package config.easteregg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import util.Util;

import java.util.Collections;
import java.util.List;

@Slf4j
public class EasterEggConfig {
    public static final EasterEggConfig instance = create();

    private static final String path = "src/main/resources/config/easter_egg.json";

    private final List<EasterEggInfo> easterEggInfos;

    @JsonCreator
    private EasterEggConfig(@JsonProperty("easterEggs") List<EasterEggInfo> easterEggInfos) {
        this.easterEggInfos = Collections.unmodifiableList(easterEggInfos);
    }

    public static EasterEggConfig create() {
        return Util.createConfig(path, EasterEggConfig.class);
    }

    public List<EasterEggInfo> getEasterEggInfos() {
        return easterEggInfos;
    }
}
