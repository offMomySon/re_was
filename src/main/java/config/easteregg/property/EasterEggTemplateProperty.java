package config.easteregg.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import config.easteregg.EasterEggType;
import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasterEggTemplateProperty {
    private static final Pattern pattern = Pattern.compile("(.*)\\{%s\\}(.*)");

    private final EasterEggType type;
    private final String format;

    @JsonCreator
    public EasterEggTemplateProperty(@NonNull @JsonProperty("type") String type,
                                     @NonNull @JsonProperty("format") String format) {
        if (format.isEmpty() || format.isEmpty()) {
            throw new RuntimeException("포멧이 빈 문자열 입니다.");
        }

        Matcher matcher = pattern.matcher(format);
        if (!matcher.find()) {
            throw new RuntimeException("포멧의 패턴이 맞지 않습니다.");
        }

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
