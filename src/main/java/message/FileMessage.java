package message;

import lombok.NonNull;
import util.IoUtil;

import java.io.BufferedReader;
import java.io.File;

public class FileMessage extends Message {
    private static final int READ_NOT_THING = 0;
    private final char[] buffer = new char[4096];

    private final File file;

    private FileMessage(@NonNull File file) {
        this.file = file;
    }

    public static FileMessage create(@NonNull String path) {
        return new FileMessage(new File(path));
    }

    @Override
    public String create() {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = IoUtil.createReader(file)) {
            int readNo = READ_NOT_THING;
            while ((readNo = reader.read(buffer)) > READ_NOT_THING) {
                sb.append(new String(buffer, 0, readNo));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
