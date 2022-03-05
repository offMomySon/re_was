package repository.easteregg;

public abstract class EasterEgg {
    protected final String content;

    public EasterEgg(String content) {
        this.content = content;
    }

    abstract String createContent();

    public String getContent() {

        return createContent();
    }


}
