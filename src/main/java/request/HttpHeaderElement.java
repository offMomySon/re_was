package request;

import lombok.NonNull;

import java.util.Collections;
import java.util.Set;

public class HttpHeaderElement {
    private final String key;
    private final Set<String> value;

    private HttpHeaderElement(@NonNull String key, @NonNull Set<String> value) {
        this.key = key;
        this.value = Collections.unmodifiableSet(value);
    }


    public static HttpHeaderElement from(String line) {

        return new HttpHeaderElement("key", Collections.emptySet());
    }

    public Set<String> getValues() {
        return value;
    }
}
