package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;
import util.Util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class RootPath {
    private final Path value;

    private RootPath(@NonNull Path value) {
        this.value = validatePath(value);
    }

    private Path validatePath(Path path) {
        if (Files.notExists(path)) {
            throw new RuntimeException("root path 가 존재하지 않습니다.");
        }
        return path;
    }

    @JsonCreator
    private static RootPath ofJackSon(@NonNull String path) {
        Path root = Util.normalizePath(Paths.get(path));

        return new RootPath(root);
    }
}
