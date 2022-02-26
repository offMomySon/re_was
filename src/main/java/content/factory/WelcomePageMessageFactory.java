package content.factory;

import config.EntryPointConfig;
import content.message.Message;
import content.message.WelcomePageMessage;

import java.nio.file.Path;

public class WelcomePageMessageFactory implements AbstractMessageFactory {
    private final Path welcomePage = EntryPointConfig.instance.getWelcomePage();
    private final Path request;

    public WelcomePageMessageFactory(Path request) {
        this.request = request;
    }

    @Override
    public Message createMessage() {
        return new WelcomePageMessage();
    }

    @Override
    public boolean isSupport() {
        return welcomePage.equals(request);
    }
}
