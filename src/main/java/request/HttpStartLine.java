package request;

import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import util.PathUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@ToString
public class HttpStartLine {
    private static final String DELIMITER = " ";
    private static final int HTTP_START_LINE_PART_SIZE = 3;

    private final HttpMethod method;
    private final Path request;
    private final String version;

    private HttpStartLine(@NonNull HttpMethod method, @NonNull Path request, @NonNull String version) {
        this.method = method;
        this.request = request;
        this.version = version;

        log.info("method = {}", method);
        log.info("target = {}", request);
        log.info("version = {}", version);
    }

    public static HttpStartLine create(@NonNull String startLine) {
        String[] delimitedLine = startLine.split(DELIMITER);
        if (delimitedLine.length < HTTP_START_LINE_PART_SIZE) {
            throw new RuntimeException("Http startLine spec 을 만족시키지 못합니다.");
        }

        for (int i = 0; i < HTTP_START_LINE_PART_SIZE; i++) {
            delimitedLine[i] = delimitedLine[i].trim();
        }

        return new HttpStartLine(
                HttpMethod.parse(delimitedLine[0]),
                PathUtil.normalizePath(Paths.get(delimitedLine[1])),
                delimitedLine[2]);
    }

    public Path getRequest() {
        return request;
    }
}
