package repository.easteregg;

import config.easteregg.EasterEgg;
import config.easteregg.EasterEggConfig;
import config.easteregg.EasterEggType;
import config.easteregg.property.EasterEggProperty;
import config.easteregg.property.EasterEggTemplateProperty;
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
    private static final Map<EasterEggType, EasterEggTemplateProperty> easterEggTemplateProperties = EasterEggConfig.instance.getEasterEggTemplateProperties();
    private static final List<EasterEggProperty> easterEggProperties = EasterEggConfig.instance.getEasterEggProperties();
    private static final Function<EasterEggProperty, EasterEgg> easterEggCreator = it -> {
        EasterEggTemplateProperty easterEggTemplateProperty = easterEggTemplateProperties.get(it.getType());
        return new EasterEgg(it.getUrl(), String.format(easterEggTemplateProperty.getFormat(), it.getContent()));
    };

    private final Map<Path, EasterEgg> easterEggs;

    private EasterEggRepository(@NonNull Map<Path, EasterEgg> easterEggs) {
        this.easterEggs = Collections.unmodifiableMap(easterEggs);
    }

    public static EasterEggRepository create() {
        Map<Path, EasterEgg> easterEggs = easterEggProperties.stream()
                .map(easterEggCreator)
                .collect(Collectors.toMap(EasterEgg::getURL, Function.identity(), (it1, it2) -> it1)
                );

        return new EasterEggRepository(easterEggs);
    }

    public Optional<EasterEgg> find(@NonNull Path path) {
        if (isExist(path)) {
            return Optional.of(easterEggs.get(path));
        }
        return Optional.empty();
    }

    public Boolean isExist(@NonNull Path path) {
        return easterEggs.containsKey(path);
    }
}
