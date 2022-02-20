package content.factory.resource;

import content.TargetPath;
import content.message.Message;
import content.message.SimpleMessage;

// TODO
// File property 를 쓰지는 않지만, Resource 와 연관된 Factory 에 대한 힌트를 주기 위해서 AbstractResourceMessageFactory 를 상속받음.
public class NotFoundMessageFactory extends TargetPathMessageFactory {
    private static final Message NOT_EXIST_MESSAGE = new SimpleMessage("Not exist file.");

    public NotFoundMessageFactory(TargetPath target) {
        super(target);
    }

    @Override
    public Message createMessage(TargetPath target) {
        return NOT_EXIST_MESSAGE;
    }

    @Override
    public boolean isSupport(TargetPath target) {
        return true;
    }
}
