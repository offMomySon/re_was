package content.factory.resource;

import content.TargetPath;
import content.message.Message;
import content.factory.AbstractMessageFactory;

public abstract class TargetPathMessageFactory implements AbstractMessageFactory {
    private final TargetPath target;

    public TargetPathMessageFactory(TargetPath target) {
        this.target = target;
    }

    @Override
    public Message createMessage() {
        return createMessage(target);
    }

    public abstract Message createMessage(TargetPath target);

    @Override
    public boolean isSupport() {
        return isSupport(target);
    }

    public abstract boolean isSupport(TargetPath target);
}
