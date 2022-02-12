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

    public static HttpBody create(@NotNull BufferedReader reader, int contentSize) {
        if (contentSize == 0) {
            log.info("content size = 0");
            return EMPTY();
        }

        char[] inputs = new char[8192];
        StringBuilder stringBuilder = new StringBuilder();

        int currentReadLength = 0;
        int readLength = 0;

        try {
            while ((readLength = reader.read(inputs)) > 0) {
                log.info("HttpBody line size = {}", readLength);
                if (currentReadLength + readLength == contentSize) {
                    break;
                }

                if (currentReadLength + readLength > contentSize) {
                    int size = currentReadLength + readLength - contentSize;
                    stringBuilder.append(inputs, 0, size);
                    break;
                }

                stringBuilder.append(inputs, 0, readLength);

                currentReadLength += readLength;
            }
        } catch (Exception e) {
            throw new RuntimeException("Http Body 읽기 실패했습니다.");
        }


        return new HttpBody(stringBuilder.toString());
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
