package config.easteregg.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import config.easteregg.EasterEggType;
import lombok.NonNull;
import util.Util;

import java.nio.file.Path;
import java.util.Objects;


public class EasterEggProperty {
    private final Path url;
    private final EasterEggType type;
    private final String content;

    public EasterEggProperty(@NonNull Path url, @NonNull String content, @NonNull EasterEggType type) {
        if (content.isBlank() || content.isEmpty()) {
            throw new RuntimeException("content 가 비어있습니다. content = '" + content + "'");
        }

        this.url = Util.normalizePath(url);
        this.content = content;
        this.type = type;
    }

    @JsonCreator
    public static EasterEggProperty ofJackSon(@NonNull @JsonProperty("url") String url,
                                              @NonNull @JsonProperty("content") String content,
                                              @NonNull @JsonProperty("type") String type) {
        Path path = Path.of(url);
        EasterEggType easterEggType = EasterEggType.parse(type);

        return new EasterEggProperty(path, content, easterEggType);
    }

    public Path getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public EasterEggType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EasterEggProperty that = (EasterEggProperty) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
