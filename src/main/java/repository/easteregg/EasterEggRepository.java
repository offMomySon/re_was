package repository.easteregg;

import config.easteregg.EasterEggConfig;
import config.easteregg.property.EasterEggProperty;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class EasterEggRepository {
    private final Map<Path, String> contents;

    private EasterEggRepository(@NonNull Map<Path, String> contents) {
        this.contents = Collections.unmodifiableMap(contents);
    }

    public static EasterEggRepository create() {
        List<EasterEggProperty> easterEggProperties = EasterEggConfig.instance.getEasterEggInfos();

//        Map<Path, String> value = easterEggInfos.stream().collect(
//                Collectors.toMap(EasterEggInfo::getUrl, it -> it.getContent(), (it1, it2) -> it1));

        return null;
//        return new EasterEggRepository(value);
    }

    public Optional<String> find(Path path) {
        if (isExist(path)) {
            return Optional.of(contents.get(path));
        }
        return Optional.empty();
    }

    public Boolean isExist(Path path) {
        return contents.containsKey(path);
    }
}
