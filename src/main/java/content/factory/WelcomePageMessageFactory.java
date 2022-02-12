package content.factory;

import config.EntryPointConfig;
import content.message.Message;
import content.message.WelcomePageMessage;

import java.nio.file.Path;

public class WelcomePageMessageFactory implements AbstractMessageFactory {
    private final Path welcomePage = EntryPointConfig.instance.getWelcomePage();
    private final Path target;

    public WelcomePageMessageFactory(Path target) {
        this.target = target;
    }

    @Override
    public Message createMessage() {
        return new WelcomePageMessage();
    }

    @Override
    public boolean isSupport() {
        return welcomePage.equals(target);
    }
}
