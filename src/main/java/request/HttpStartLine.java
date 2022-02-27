package request;

import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import util.Util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

@Slf4j
@ToString
public class HttpStartLine {
    private static final String DELIMITER = " ";
    private static final int HTTP_START_LINE_PART_SIZE = 3;

    private final HttpMethod method;
    private final Path request;
    private final String version;

    private HttpStartLine(HttpMethod method, @NonNull Path request, @NonNull String version) {
        this.method = method;
        this.request = request;
        this.version = version;

        log.info("method = {}", method);
        log.info("target = {}", request);
        log.info("version = {}", version);
    }

    public static HttpStartLine create(@NonNull String startLine) {
        String[] delimitedLine = startLine.trim().split(DELIMITER);

        if (delimitedLine.length != HTTP_START_LINE_PART_SIZE) {
            throw new HttpStartLineException("Http startLine spec 을 만족시키지 못합니다.");
        }

        String method = delimitedLine[0].trim();
        String target = delimitedLine[1].trim();
        String version = delimitedLine[2].trim();

        // method검증 잘못돼면 httpStartLineException을 발생

        try {
            return new HttpStartLine(
                    HttpMethod.parse(method),
                    Util.normalizePath(Paths.get(target)),
                    version);
        } catch (Exception e) {
            throw new HttpStartLineException("생성 오류", e);
        }
    }

    public Path getRequest() {
        return request;
    }

    private static <T> T nonException(Supplier<T> supplier) {
        return nonException(supplier, "생성 오류");
    }

    private static <T> T nonException(Supplier<T> supplier, String exceptionMessage) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new HttpStartLineException(exceptionMessage, e);
        }
    }
}
