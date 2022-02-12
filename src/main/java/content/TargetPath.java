package content;

import config.DownloadConfig;
import lombok.NonNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TargetPath {
    private final Path root = DownloadConfig.instance.getRoot();
    private final Path request;

    public TargetPath(@NonNull Path request) {
        this.request = request;
    }

    public Path get() {
        return Paths.get(root.toString(), request.toString());
    }

    public File toFile() {
        return get().toFile();
    }
}
