package config.easteregg;

import java.util.Arrays;

public enum EasterEggType {

    DEFAULT("default"),
    EVENT("event"),
    SPECIAL("special");

    private final String value;

    EasterEggType(String value) {
        this.value = value;
    }

    public static EasterEggType parse(String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("일치하는 Event type 이 아닙니다."));
    }
}
