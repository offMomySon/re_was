package content.factory;

import content.message.Message;

public interface AbstractMessageFactory {
    Message createMessage();

    boolean isSupport();
}
