package repository.easteregg;

public class SpecialEasterEgg extends EasterEgg {
    private static final String format = "이것은 special easter egg!, {%s}";

    public SpecialEasterEgg(String content) {
        super(content);
    }

    @Override
    public String createContent() {
        return String.format(format, content);
    }
}
