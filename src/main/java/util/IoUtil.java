package util;

import lombok.NonNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class IoUtil {
    public static BufferedReader createReader(@NonNull InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), StandardCharsets.UTF_8));
    }

    public static BufferedReader createReader(@NonNull File file) {
        BufferedReader reader = null;
        try {
            reader = createReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file stream 생성을 실패 했습니다.");
        }
        return reader;
    }
}
