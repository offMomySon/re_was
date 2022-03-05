package config.easteregg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import util.Util;

import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;


public class EasterEggInfo {
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
    private static final String contentFormat = "%s presented %s, take it!!";

    private final Path url;
    private final String content;
    private final EasterEggType type;

    public EasterEggInfo(@NonNull Path url, @NonNull String content, @NonNull EasterEggType type) {
        this.url = Util.normalizePath(url);
        this.content = content;
        this.type = type;
    }

    @JsonCreator
    public static EasterEggInfo ofJackSon(@NonNull @JsonProperty("url") String url,
                                          @NonNull @JsonProperty("content") String content,
                                          @NonNull @JsonProperty("type") String type) {
        Path path = Path.of(url);
        EasterEggType easterEggType = EasterEggType.parse(type);
        
        return new EasterEggInfo(path, content, easterEggType);
    }

    public Path getUrl() {
        return url;
    }

    public String getContent() {
        return String.format(contentFormat, dateFormat.format(new Date()), content);
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
