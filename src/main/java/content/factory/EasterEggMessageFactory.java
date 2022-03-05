package content.factory;

import config.easteregg.EasterEggInfo;
import content.message.Message;
import content.message.SimpleMessage;
import lombok.NonNull;
import repository.easteregg.EasterEggRepository;

import java.nio.file.Path;

public class EasterEggMessageFactory implements AbstractMessageFactory {
    private final Path path;
    private final EasterEggRepository easterEggRepository;

    public EasterEggMessageFactory(@NonNull Path path) {
        this.path = path;
        this.easterEggRepository = EasterEggRepository.instance;
    }

    @Override
    public Message createMessage() {
        EasterEggInfo easterEggInfo = easterEggRepository.find(path)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 path 입니다. path = '" + path + "'"));

        return new SimpleMessage(easterEggInfo.getContent());
    }

    @Override
    public boolean isSupport() {
        return easterEggRepository.isExist(path);
    }
}