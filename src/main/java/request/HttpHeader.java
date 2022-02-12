package request;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class HttpHeader {
    private static final String READ_CONTENT_LENGTH = "Content-Length";

    private final Map<String, List<String>> map;

    private HttpHeader(@NonNull Map<String, List<String>> map) {
        this.map = map;

        for (String key : map.keySet()) {
            log.info("Key = {}", key);

            List<String> values = map.get(key);
            for (String value : values) {
                log.info("{}", value);
            }
        }
    }

    public int getContentLength() {
        String contentLength = map.computeIfAbsent(READ_CONTENT_LENGTH, key -> List.of("0")).get(0);

        return Integer.parseInt(contentLength);
    }

    // string 받자마자 map 생성
    // http header 와 inputStream 은 격리됨.
    public static class Builder {
        private static final String KEY_VALUE_DELIMITER = ":";
        private static final String VALUE_DELIMITER = ",";
        private static final int KEY_VALUE_SIZE = 2;

        private final Map<String, List<String>> map = new HashMap();

        public Builder append(String line) {
            String[] keyValueSplit = line.split(KEY_VALUE_DELIMITER, KEY_VALUE_SIZE);

            if (keyValueSplit.length < KEY_VALUE_SIZE) {
                throw new RuntimeException("Key value 를 정상적으로 받지 못했습니다.");
            }

            for (int i = 0; i < KEY_VALUE_SIZE; i++) {
                keyValueSplit[i] = keyValueSplit[i].trim();
            }

            String key = keyValueSplit[0];
            String[] valueSplited = keyValueSplit[1].split(VALUE_DELIMITER);

            if (valueSplited.length <= 0) {
                throw new RuntimeException("key 에 대한 value 가 존재하지 않습니다.");
            }

            for (int i = 0; i < valueSplited.length; i++) {
                valueSplited[i] = valueSplited[i].trim();
            }

            List<String> values = Arrays.asList(valueSplited);
            map.computeIfAbsent(key, k -> new LinkedList<>()).addAll(values);
            return this;
        }

        public HttpHeader build() {
            return new HttpHeader(map);
        }
    }
}
