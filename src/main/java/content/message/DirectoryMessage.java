package content.message;

import content.TargetPath;
import lombok.NonNull;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class DirectoryMessage extends Message {
    private final File directory;

    private DirectoryMessage(@NonNull File directory) {
        this.directory = directory;
    }

    public static DirectoryMessage from(@NonNull TargetPath target) {
        return new DirectoryMessage(target.toFile());
    }

    @Override
    public String create() {
        Arrays.stream(directory.listFiles())
                .forEach(_file -> content.append(_file).append("</br>"));

        return content.toString();
    }
}
