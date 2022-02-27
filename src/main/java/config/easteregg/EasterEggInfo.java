package config.easteregg;

import lombok.NonNull;
import util.Util;

import java.nio.file.Path;


public class EasterEggInfo {
    private final Path url;
    private final String content;

    public EasterEggInfo(@NonNull Path url, @NonNull String content) {
        this.url = Util.normalizePath(url);
        this.content = content;
    }

    public static EasterEggInfo ofJackSon(@NonNull String url, @NonNull String content) {
        Path path = Path.of(url);
        return new EasterEggInfo(path, content);
    }

    public Path getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}
