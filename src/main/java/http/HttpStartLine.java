package http;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.PathUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class HttpStartLine {
    private static final String DELIMITER = " ";
    private static final int HTTP_START_LINE_PART_SIZE = 3;
    private static final String UNBOUNDED = null;

    private final HttpMethod method;
    private final Path target;
    private final String version;

    private HttpStartLine(@NonNull HttpMethod method, @NonNull Path target, @NonNull String version) {
        this.method = method;
        this.target = target;
        this.version = version;

        log.info("method {}", this.method);
        log.info("url {}", this.target);
        log.info("version {}", this.version);
    }

    public static HttpStartLine create(@NonNull BufferedReader reader) {
        String line = UNBOUNDED;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("StartLine 읽기 실패.");
        }

        String[] delimitedLine = line.split(DELIMITER);
        if (delimitedLine.length < HTTP_START_LINE_PART_SIZE) {
            throw new RuntimeException("HTTP START LINE spec 을 만족시키지 못합니다.");
        }

        return new HttpStartLine(
                HttpMethod.parse(delimitedLine[0]),
                PathUtil.removeRelativePath(Paths.get(delimitedLine[1])),
                delimitedLine[2]);
    }

    public Path getTarget() {
        return target;
    }
}
