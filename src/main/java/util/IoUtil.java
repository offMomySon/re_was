package util;

import lombok.NonNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IoUtil {
    public static BufferedReader createReader(@NonNull InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), StandardCharsets.UTF_8));
    }

    public static BufferedReader createReader(@NonNull File file) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
