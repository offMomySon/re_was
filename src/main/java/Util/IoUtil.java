package Util;

import lombok.NonNull;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class IoUtil {
    public static BufferedReader createReader (@NonNull InputStream inputStream){
        return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), StandardCharsets.UTF_8));
    }
}
