package request;

import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

@Getter
public class HttpRequest {
    private static final String UNBOUNDED = null;

    private final HttpStartLine httpStartLine;
    private final HttpHeader httpHeader;
    private final HttpBody httpBody;

    private HttpRequest(@NotNull HttpStartLine httpStartLine, @NotNull HttpHeader httpHeader, @NotNull HttpBody httpBody) {
        this.httpStartLine = httpStartLine;
        this.httpHeader = httpHeader;
        this.httpBody = httpBody;
    }

    public static HttpRequest create(@NotNull InputStream inputStream) {
        BufferedReader reader = Util.createReader(inputStream);

        HttpStartLine httpStartLine = createHttpStartLine(reader);
        HttpHeader httpHeader = createHttpHeader(reader);
        HttpBody httpBody = createHttpBody(reader, httpHeader.getContentLength());

        return new HttpRequest(httpStartLine, httpHeader, httpBody);
    }

    private static HttpStartLine createHttpStartLine(@NonNull BufferedReader reader) {
        String startLine = UNBOUNDED;

        try {
            startLine = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("startLine 를 읽어오지 못했습니다.");
        }

        return HttpStartLine.create(startLine);
    }

    private static HttpHeader createHttpHeader(@NonNull BufferedReader reader) {
        String header = UNBOUNDED;
        HttpHeader.Builder httpHeaderBuilder = new HttpHeader.Builder();

        try {
            while ((header = reader.readLine()) != UNBOUNDED && !header.isEmpty()) {
                httpHeaderBuilder.append(HttpHeaderElement.from(header));
            }
        } catch (IOException e) {
            throw new RuntimeException("header 를 읽어오지 못했습니다.");
        }

        return httpHeaderBuilder.build();
    }

    private static HttpBody createHttpBody(@NonNull BufferedReader reader, int contentLength) {
        String body = UNBOUNDED;
        HttpBody httpBody = HttpBody.EMPTY();
        HttpBody.Builder httpBodyBuilder = new HttpBody.Builder(contentLength);

        if (httpBodyBuilder.isNeedRead()) {
            try {
                while ((body = reader.readLine()) != UNBOUNDED &&
                        !body.isEmpty() &&
                        httpBodyBuilder.isNeedRead()) {
                    httpBodyBuilder.append(body);
                }
            } catch (IOException e) {
                throw new RuntimeException("body 를 읽어오지 못했습니다.");
            }

            httpBody = httpBodyBuilder.build();
        }

        return httpBody;
    }
}
