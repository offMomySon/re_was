package request;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class HttpHeaderElement {
    private static final String KEY_VALUE_DELIMITER = ":";
    private static final String VALUE_DELIMITER = ",";
    private static final int KEY_VALUE_DELIMITER_SIZE = 2;

    private final String key;
    private final Set<String> value;

    public HttpHeaderElement(@NonNull String key, @NonNull Set<String> value) {
        this.key = key;
        this.value = Collections.unmodifiableSet(value);
    }


    public static HttpHeaderElement from(String line) {
        if (!line.contains(KEY_VALUE_DELIMITER)) {
            throw new HttpHeaderElementException("key 구분자가 존재하지 않습니다.");
        }

        log.info("{}", line);
        String[] splitLine = line.split(KEY_VALUE_DELIMITER, KEY_VALUE_DELIMITER_SIZE);

        String key = splitLine[0].trim();
        String value = splitLine[1];

        if (StringUtils.containsWhitespace(key) || key.isEmpty()) {
            throw new HttpHeaderElementException("key 가 존재하지 않습니다. key = '" + key + "'");
        }

        String[] valueElements = createValueElement(value);

        return new HttpHeaderElement(key, new HashSet(Arrays.asList(valueElements)));
    }

    private static String[] createValueElement(String value) {
        if (value.isEmpty()) {
            throw new HttpHeaderElementException("value 가 존재하지 않습니다.");
        }

        String[] valueElements = value.split(VALUE_DELIMITER);

        for (String valueElement : valueElements) {
            valueElement = valueElement.trim();

            if (valueElement.isBlank() || valueElement.isEmpty()) {
                throw new HttpHeaderElementException("유효하지 않는 value 입니다. value = '" + valueElement + "'");
            }
        }

        return valueElements;
    }

    public String getKey() {
        return key;
    }

    public Set<String> getValues() {
        return value;
    }

    public String getAnyValue() {
        return value.iterator().next();
    }
}
