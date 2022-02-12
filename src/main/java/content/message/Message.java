package content.message;

public abstract class Message {
    protected StringBuilder content = new StringBuilder();

    public abstract String create();
}