package message;

import lombok.NonNull;

import java.io.File;
import java.util.Arrays;

public class DirectoryMessage extends Message {
    private final File directory;

    private DirectoryMessage(@NonNull File directory) {
        this.directory = directory;
    }

    public static DirectoryMessage create(@NonNull String path) {
        return new DirectoryMessage(new File(path));
    }

    @Override
    public String create() {
        StringBuilder fileNames = new StringBuilder();

        Arrays.stream(directory.listFiles())
                .forEach(_file -> fileNames.append(fileNames.append(_file).append("</br>")));

        return fileNames.toString();
    }
}
