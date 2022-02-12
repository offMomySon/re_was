package content;

import config.DownloadConfig;
import config.RootPath;
import lombok.NonNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

// Todo
// 책임 - 데이터 성격.
// 컨셉 - 리퀘스트에 대한 실제 위치
public class TargetPath {
    private final RootPath root = DownloadConfig.instance.getRootPath();
    private final Path request;

    public TargetPath(@NonNull Path request) {
        this.request = request;
    }

    public Path get() {
        return Paths.get(root.getValue().toString(), request.toString());
    }

    public File toFile() {
        return get().toFile();
    }
}
