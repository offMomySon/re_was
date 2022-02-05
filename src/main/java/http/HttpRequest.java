package http;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import util.IoUtil;

import java.io.BufferedReader;
import java.io.InputStream;

@Getter
public class HttpRequest {
    private final HttpStartLine httpStartLine;
    private final HttpHeader httpHeader;
    private final HttpBody httpBody;

    private HttpRequest(@NotNull HttpStartLine httpStartLine, @NotNull HttpHeader httpHeader, @NotNull HttpBody httpBody) {
        this.httpStartLine = httpStartLine;
        this.httpHeader = httpHeader;
        this.httpBody = httpBody;
    }

    public static HttpRequest create(@NotNull InputStream inputStream) {
        BufferedReader bufferedReader = IoUtil.createReader(inputStream);

        HttpStartLine httpStartLine = HttpStartLine.create(bufferedReader);
        HttpHeader httpHeader = HttpHeader.create(bufferedReader);
        HttpBody httpBody = HttpBody.create(bufferedReader, httpHeader.getContentLength());

        return new HttpRequest(httpStartLine, httpHeader, httpBody);
    }
}
