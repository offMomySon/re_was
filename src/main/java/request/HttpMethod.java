package request;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

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

    public static HttpMethod parse(String method) {
        String uppercaseMethodName = Optional.ofNullable(method).map(String::toUpperCase).orElseThrow(() -> new HttpMethodException("dsf"));

        HttpMethod httpMethod = Arrays.stream(HttpMethod.values())
                .filter(_method -> _method.value.equals(uppercaseMethodName))
                .findFirst()
                .orElseThrow(() -> new HttpMethodException("Http method 가 없습니다."));

        log.info("Inserted Http method = {}", method.toUpperCase());
        return httpMethod;
    }
}
