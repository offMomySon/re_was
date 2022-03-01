package repository.easteregg;

import config.easteregg.EasterEggConfig;
import config.easteregg.EasterEggInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class EasterEggRepository {
    public static final EasterEggRepository instance = create();

    private final Map<Path, EasterEggInfo> easterEggInfos;

    private EasterEggRepository(@NonNull Map<Path, EasterEggInfo> easterEggInfos) {
        this.easterEggInfos = Collections.unmodifiableMap(easterEggInfos);
    }

    private static EasterEggRepository create() {
        List<EasterEggInfo> _easterEggInfos = EasterEggConfig.instance.getEasterEggInfos();

        Map<Path, EasterEggInfo> easterEggInfos = _easterEggInfos.stream().collect(Collectors.toMap(
                EasterEggInfo::getUrl, Function.identity(), (it1, it2) -> it1
        ));

        return new EasterEggRepository(easterEggInfos);
    }

    public EasterEggInfo find(Path path) {
        return easterEggInfos.getOrDefault(path, EasterEggInfo.EMPTY_EASTER_EGG);
    }

    public Boolean isExist(Path path) {
        return easterEggInfos.containsKey(path);
    }
}
