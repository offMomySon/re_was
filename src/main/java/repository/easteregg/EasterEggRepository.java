package repository.easteregg;

import config.easteregg.EasterEggConfig;
import config.easteregg.EasterEggInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        List<EasterEggInfo> easterEggInfos = EasterEggConfig.instance.getEasterEggInfos();

        Map<Path, EasterEggInfo> value = easterEggInfos.stream().collect(Collectors.toMap(
                EasterEggInfo::getUrl, Function.identity(), (it1, it2) -> it1
        ));

        return new EasterEggRepository(value);
    }

    public Optional<EasterEggInfo> find(Path path) {
        if (isExist(path)) {
            return Optional.of(easterEggInfos.get(path));
        }
        return Optional.empty();
    }

    public Boolean isExist(Path path) {
        return easterEggInfos.containsKey(path);
    }
}
