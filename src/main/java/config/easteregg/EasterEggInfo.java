package config.easteregg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import util.Util;

import java.nio.file.Path;
import java.util.Objects;


public class EasterEggInfo {
    public static final EasterEggInfo EMPTY_EASTER_EGG = new EasterEggInfo(Path.of(""), "EMPTY EASTER EGG.");

    private final Path url;
    private final String content;

    public EasterEggInfo(@NonNull Path url, @NonNull String content) {
        this.url = Util.normalizePath(url);
        this.content = content;
    }

    @JsonCreator
    public static EasterEggInfo ofJackSon(@NonNull @JsonProperty("url") String url,
                                          @NonNull @JsonProperty("content") String content) {
        Path path = Path.of(url);
        return new EasterEggInfo(path, content);
    }

    public Path getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EasterEggInfo that = (EasterEggInfo) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
