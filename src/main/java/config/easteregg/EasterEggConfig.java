package config.easteregg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import config.easteregg.property.EasterEggProperty;
import config.easteregg.property.EasterEggTemplateProperty;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.Util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class EasterEggConfig {
    public static final EasterEggConfig instance = create();

    private static final String path = "src/main/resources/config/easter_egg.json";

    private final List<EasterEggProperty> easterEggProperties;
    private final Map<EasterEggType, EasterEggTemplateProperty> easterEggTemplateProperties;

    private EasterEggConfig(@NonNull List<EasterEggProperty> easterEggProperties,
                            @NonNull Map<EasterEggType, EasterEggTemplateProperty> easterEggTemplateProperties) {
        this.easterEggProperties = Collections.unmodifiableList(easterEggProperties);
        this.easterEggTemplateProperties = Collections.unmodifiableMap(easterEggTemplateProperties);
    }

    @JsonCreator
    public static EasterEggConfig ofJackSon(@NonNull @JsonProperty("easterEggs") List<EasterEggProperty> easterEggProperties,
                                            @NonNull @JsonProperty("template") List<EasterEggTemplateProperty> _easterEggTemplateProperties) {

        Map<EasterEggType, EasterEggTemplateProperty> easterEggTemplateProperties = _easterEggTemplateProperties.stream()
                .collect(Collectors.toMap(EasterEggTemplateProperty::getEasterEggType, Function.identity(), (it, it2) -> it));

        return new EasterEggConfig(easterEggProperties, easterEggTemplateProperties);
    }

    public static EasterEggConfig create() {
        return Util.createConfig(path, EasterEggConfig.class);
    }

    public List<EasterEggProperty> getEasterEggProperties() {
        return easterEggProperties;
    }

    public Map<EasterEggType, EasterEggTemplateProperty> getEasterEggTemplateProperties() {
        return easterEggTemplateProperties;
    }
}
