package content.factory.resource;

import content.TargetPath;
import content.message.DirectoryMessage;
import content.message.Message;

import java.io.File;

public class DirectoryMessageFactory extends TargetPathMessageFactory {
    public DirectoryMessageFactory(TargetPath target) {
        super(target);
    }

    @Override
    public Message createMessage(TargetPath target) {
        return DirectoryMessage.from(target);
    }

    @Override
    public boolean isSupport(TargetPath target) {
        File targetFile = target.toFile();

        return targetFile.exists() ? targetFile.isDirectory() : false;
    }
}
