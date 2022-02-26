package request;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTION("OPTION");

    private final String value;

    HttpMethod(@NonNull String value) {
        this.value = value;
    }

    public static HttpMethod parse(@NonNull String value) {
        log.info("Http method = {}", value.toUpperCase());

        return Arrays.stream(values())
                .filter(method -> method.value.equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Http method 가 아닙니다."));
    }
}
