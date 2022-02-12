package content.factory;

import lombok.NonNull;
import content.message.Message;

import java.util.Collections;
import java.util.List;

public class CompositeAbstractMessageFactory implements AbstractMessageFactory {
    private final List<AbstractMessageFactory> messageFactories;

    public CompositeAbstractMessageFactory(@NonNull List<AbstractMessageFactory> messageFactories) {
        this.messageFactories = Collections.unmodifiableList(messageFactories);
    }

    @Override
    public Message createMessage() {
        return messageFactories.stream()
                .filter(AbstractMessageFactory::isSupport)
                .findFirst()
                .map(m -> m.createMessage())
                .orElseThrow();
    }

    @Override
    public boolean isSupport() {
        return true;
    }
}
