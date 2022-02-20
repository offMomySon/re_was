package request;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;

@Slf4j
public class HttpBody {
    private final String value;

    private HttpBody(@NotNull String value) {
        this.value = value;
    }

    public static HttpBody EMPTY() {
        log.info("create empty body");

        return new HttpBody("");
    }

    public static class Builder {
        private final StringBuilder sb = new StringBuilder();
        private final int contentSize;

        public Builder(int contentSize) {
            this.contentSize = contentSize;
        }

        public boolean isNeedRead() {
            return sb.length() < contentSize ? true : false;
        }

        public Builder append(@NonNull String s) {
            sb.append(s);
            return this;
        }

        public HttpBody build() {
            return new HttpBody(sb.toString());
        }
    }
}
