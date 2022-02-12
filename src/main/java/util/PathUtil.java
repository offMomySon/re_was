package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class PathUtil {

    public static Path normalizePath(Path requestTarget) {
        Stack<String> pathStack = new Stack<>();
        String[] splitPath = requestTarget.toString().split("/");

        for (String path : splitPath) {
            if (path.length() == 0) {
                continue;
            }
            if (!path.equals("..")) {
                pathStack.push(path);
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
