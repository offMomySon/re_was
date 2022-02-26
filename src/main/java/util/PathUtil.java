package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class PathUtil {

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
                throw new RuntimeException("상대경로를 벗어나면 안됩니다.");
            }

            pathStack.pop();
        }
        return Paths.get("/", String.join("/", pathStack));
    }
}
