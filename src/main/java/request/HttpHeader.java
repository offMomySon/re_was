package request;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class HttpHeader {
    private static final String READ_CONTENT_LENGTH = "Content-Length";

    private final Map<String, HttpHeaderElement> httpHeaderElements;

    public HttpHeader(@NonNull Map<String, HttpHeaderElement> httpHeaderElements) {
        this.httpHeaderElements = httpHeaderElements;
    }

    public static HttpHeader create(@NonNull List<HttpHeaderElement> httpHeaderElements) {
        Map<String, HttpHeaderElement> value = httpHeaderElements.stream().collect(Collectors.toMap(
                HttpHeaderElement::getKey, Function.identity(), (it1, it2) -> it1)
        );

        return new HttpHeader(value);
    }

    public int getContentLength() {
        HttpHeaderElement httpHeaderElement = httpHeaderElements
                .computeIfAbsent(READ_CONTENT_LENGTH, k -> new HttpHeaderElement(READ_CONTENT_LENGTH, Set.of("0")));

        Set<String> httpHeaderElementValues = httpHeaderElement.getValues();

        int contentLength = Integer.parseInt(httpHeaderElementValues.iterator().next());

        return contentLength;
    }

    // string 받자마자 map 생성
    // http header 와 inputStream 은 격리됨.
    public static class Builder {
        private final List<HttpHeaderElement> httpHeaderElements = new ArrayList<>();

        public Builder append(@NonNull HttpHeaderElement httpHeaderElement) {
            httpHeaderElements.add(httpHeaderElement);
            return this;
        }

        public HttpHeader build() {
            return HttpHeader.create(httpHeaderElements);
        }
    }
}
