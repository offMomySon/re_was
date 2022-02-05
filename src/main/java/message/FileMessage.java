package message;

import lombok.NonNull;
import util.IoUtil;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Path;

public class FileMessage extends Message {
    private static final int READ_NOT_THING = 0;
    private final char[] buffer = new char[4096];

    private final File file;

    private FileMessage(@NonNull File file) {
        this.file = file;
    }

    public static FileMessage from(@NonNull Path target) {
        return new FileMessage(target.toFile());
    }

    @Override
    public String create() {
        try (BufferedReader reader = IoUtil.createReader(file)) {
            int readNo = READ_NOT_THING;
            while ((readNo = reader.read(buffer)) > READ_NOT_THING) {
                content.append(new String(buffer, 0, readNo));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }
}
