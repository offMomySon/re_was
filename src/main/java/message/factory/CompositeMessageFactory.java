package message.factory;

import lombok.NonNull;
import message.Message;

import java.util.Collections;
import java.util.List;

public class CompositeMessageFactory implements MessageFactory {
    private final List<MessageFactory> messageFactories;

    public CompositeMessageFactory(@NonNull List<MessageFactory> messageFactories) {
        this.messageFactories = Collections.unmodifiableList(messageFactories);
    }

    @Override
    public Message createMessage() {
        return messageFactories.stream()
                .filter(MessageFactory::isSupport)
                .findFirst()
                .map(m -> m.createMessage())
                .orElseThrow();
    }

    @Override
    public boolean isSupport() {
        return true;
    }
}
