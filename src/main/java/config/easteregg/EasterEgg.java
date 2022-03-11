package config.easteregg;

import lombok.NonNull;
import util.Util;

import java.nio.file.Path;

public class EasterEgg {

    private final Path path;
    private final String content;

    public EasterEgg(@NonNull Path path, @NonNull String content) {
        this.path = Util.normalizePath(path);
        this.content = content;
    }

    public Path getURL() {
        return path;
    }

    public String getContent() {
        return content;
    }
}
