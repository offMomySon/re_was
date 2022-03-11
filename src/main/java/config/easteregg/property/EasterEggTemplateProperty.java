package config.easteregg.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import config.easteregg.EasterEggType;
import lombok.NonNull;

public class EasterEggTemplateProperty {
    private final EasterEggType type;
    private final String format;

    @JsonCreator
    public EasterEggTemplateProperty(@NonNull @JsonProperty("type") String type,
                                     @NonNull @JsonProperty("format") String format) {
        this.type = EasterEggType.parse(type);
        this.format = format;
    }

    public EasterEggType getEasterEggType() {
        return type;
    }

    public String getFormat() {
        return format;
    }
}
