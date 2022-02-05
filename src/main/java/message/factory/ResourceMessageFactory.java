package message.factory;

import lombok.NonNull;
import message.Message;
import message.ResourceMessageCreator;
import message.SimpleMessage;

public class ResourceMessageFactory implements MessageFactory {
    private static final Message TARGET_NOT_EXIST = new SimpleMessage("Target not exist.");

    private final Message message;

    private ResourceMessageFactory(@NonNull Message message) {
        this.message = message;
    }

    public static ResourceMessageFactory from(@NonNull ResourceMessageCreator resourceMessageCreator) {
        Message message = resourceMessageCreator.create()
                .orElse(TARGET_NOT_EXIST);
        return new ResourceMessageFactory(message);
    }

    @Override
    public Message createMessage() {
        if (message.equals(TARGET_NOT_EXIST)) {
            return TARGET_NOT_EXIST;
        }
        return message;
    }

    @Override
    public boolean isSupport() {
        return true;
    }
}
