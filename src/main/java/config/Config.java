package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class Config<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    // TODO
    // clazz, path 가 config class 에 충분한 컨셉을 담아 줄 수 있는가?
    // path 와 class 로 파일로 부터 객체를 생성한다. -> config 컨셉은 아닌거 같은데.
    // 인스턴스의 변수들을 제한 하는것이 컨셉이라 생각되는데 ( ex. 파일이름이 *config.json 처럼? )
    // 담을 컨셉이 없어서 차라리 유틸이 더 좋아 보인다.
    private final Class<T> clazz;
    private final String path;

    public Config(Class<T> clazz, String path) {
        this.clazz = clazz;
        this.path = validatePath(path);
    }

    private String validatePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("path [" + path + "] 파일이 존재하지 않습니다.");
        }
        return path;
    }

    public T create() {
        try {
            return objectMapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
