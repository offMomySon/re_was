package config.easteregg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import config.easteregg.property.EasterEggProperty;
import config.easteregg.property.EasterEggTemplateProperty;
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
    private final List<EasterEggTemplateProperty> easterEggTemplateProperties;
    private final List<EasterEgg> easterEggs;

    @JsonCreator

    public EasterEggConfig(@JsonProperty("easterEggs") List<EasterEggProperty> easterEggProperties,
                           @JsonProperty("template") List<EasterEggTemplateProperty> easterEggTemplateProperties) {
        this.easterEggProperties = Collections.unmodifiableList(easterEggProperties);
        this.easterEggTemplateProperties = Collections.unmodifiableList(easterEggTemplateProperties);

        Map<EasterEggType, EasterEggTemplateProperty> easterEggTemplates = easterEggTemplateProperties.stream()
                .collect(Collectors.toMap(EasterEggTemplateProperty::getEasterEggType, Function.identity(), (it, it2) -> it));

        Function<EasterEggProperty, EasterEgg> easterEggCreator = it -> {
            EasterEggTemplateProperty easterEggTemplateProperty = easterEggTemplates.get(it.getType());
            return new EasterEgg(it.getUrl(), String.format(easterEggTemplateProperty.getFormat(), it.getContent()));
        };

        easterEggs = easterEggProperties.stream().map(easterEggCreator).collect(Collectors.toList());

    }

    public static EasterEggConfig create() {
        return Util.createConfig(path, EasterEggConfig.class);
    }

    public List<EasterEggProperty> getEasterEggInfos() {
        return easterEggProperties;
    }
}
