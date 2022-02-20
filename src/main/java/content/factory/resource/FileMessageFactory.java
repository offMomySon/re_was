package content.factory.resource;

import content.TargetPath;
import content.message.FileMessage;
import content.message.Message;

import java.io.File;

public class FileMessageFactory extends TargetPathMessageFactory {
    public FileMessageFactory(TargetPath target) {
        super(target);
    }

    @Override
    public Message createMessage(TargetPath target) {
        return FileMessage.create(target);
    }

    @Override
    public boolean isSupport(TargetPath target) {
        File targetFile = target.toFile();

        return targetFile.exists() ? targetFile.isFile() : false;
    }
}
