package repository.easteregg;

public class DefaultEasterEgg extends EasterEgg {
    private static final String format = "이것은 default easter egg!, {%s}";

    public DefaultEasterEgg(String content) {
        super(content);
    }

    @Override
    public String createContent() {
        return String.format(format, content);
    }
}
