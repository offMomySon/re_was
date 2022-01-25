package http;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.util.*;

@Slf4j
public class HttpHeader {
    private static final String KEY_VALUE_DELIMITER = ":";
    private static final String VALUE_DELIMITER = ",";
    private static final String READ_CONTENT_LENGTH = "Content-Length";
    private static final int KEY_VALUE_SIZE = 2;

    private static final String UNBOUNDED = null;

    private final Map<String, List<String>> map;

    private HttpHeader(@NonNull Map<String, List<String>> map) {
        this.map = map;
    }

    public int getContentLength() {
        List<String> len = map.computeIfAbsent(READ_CONTENT_LENGTH, c -> List.of("0"));

        return Integer.parseInt(len.get(0));
    }

    public static HttpHeader create(@NonNull BufferedReader reader) {
        Map<String, List<String>> map = new HashMap();
        String line = UNBOUNDED;

        try {
            while ((line = reader.readLine()) != UNBOUNDED && !line.isEmpty()) {
                log.info("readLine = {}", line);

                String[] keyValueSplit = line.split(KEY_VALUE_DELIMITER, KEY_VALUE_SIZE);

                if (keyValueSplit.length < KEY_VALUE_SIZE) {
                    throw new RuntimeException("Key value 를 정상적으로 받지 못했습니다.");
                }

                String key = keyValueSplit[0];
                List<String> values = Arrays.asList(keyValueSplit[1].split(VALUE_DELIMITER));

                if (values.isEmpty()) {
                    throw new RuntimeException("key 에 대한 value 가 존재하지 않습니다.");
                }

                map.computeIfAbsent(key, k -> new LinkedList<>())
                        .addAll(values);
            }
        } catch (Exception e) {
            throw new RuntimeException("HttpHeader 를 읽는중 에러가 발생했습니다. ");
        }

        return new HttpHeader(map);
    }
}
