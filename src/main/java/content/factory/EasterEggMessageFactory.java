package content.factory;

import config.easteregg.EasterEgg;
import content.message.Message;
import content.message.SimpleMessage;
import lombok.NonNull;
import repository.easteregg.EasterEggRepository;

import java.nio.file.Path;

public class EasterEggMessageFactory implements AbstractMessageFactory {
    private final Path path;
    private final EasterEggRepository easterEggRepository;

    private EasterEggMessageFactory(@NonNull Path path, @NonNull EasterEggRepository easterEggRepository) {
        this.path = path;
        this.easterEggRepository = easterEggRepository;
    }

    @Override
    public Message createMessage() {
        EasterEgg easterEgg = easterEggRepository.find(path)
                .orElseThrow(() -> new RuntimeException("easter egg 에 존재하지 않는 path 입니다. path = '" + path + "'"));

        return new SimpleMessage(easterEgg.getContent());
    }

    @Override
    public boolean isSupport() {
        return easterEggRepository.isExist(path);
    }
}
