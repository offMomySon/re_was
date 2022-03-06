package repository.easteregg;

public class EventEasterEgg extends EasterEgg {
    private static final String format = "이것은 event easter egg!, {%s}";

    public EventEasterEgg(String content) {
        super(content);
    }

    @Override
    public String createContent() {
        return String.format(format, content);
    }
}
