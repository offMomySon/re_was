package config.easteregg;

import java.util.Arrays;

public enum EasterEggType {

    DEFAULT("default", "이것은 default easter egg!, {%s}"),
    EVENT("event", "이것은 event easter egg!, {%s}"),
    SPECIAL("special", "이것은 special easter egg!, {%s}");

    private final String value;
    private final String format;

    EasterEggType(String value, String format) {
        this.value = value;
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public static EasterEggType parse(String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("일치하는 Event type 이 아닙니다."));
    }

    public String toContent(String content) {
        return String.format(format, content);
    }


}
