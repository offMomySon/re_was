package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class Util {
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


    public static Path normalizePath(Path path) {
        Stack<String> pathStack = new Stack<>();
        String[] splitPath = path.toString().split("/");

        for (String pathPart : splitPath) {
            if (pathPart.length() == 0) {
                continue;
            }
            if (!pathPart.equals("..")) {
                pathStack.push(pathPart);
                continue;
            }

            if (pathStack.isEmpty()) {
                throw new PathException("상대경로를 벗어나면 안됩니다.");
            }

            pathStack.pop();
        }
        return Paths.get("/", String.join("/", pathStack));
    }

    public static <T> T createConfig(String path, Class<T> clazz) {
        validateExistPath(path);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            throw new ConfigException("파일을 class 파일로 만드는데 실패 했습니다.");
        }
    }

    private static void validateExistPath(String path) {
        if (Files.notExists(Path.of(path))) {
            throw new ConfigException("파일이 존재하지 않습니다.");
        }
    }
}
