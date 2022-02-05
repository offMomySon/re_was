package message;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleMessage extends Message {
    private final String message;

    public SimpleMessage(@NonNull String message) {
        this.message = message;
    }

    @Override
    public String create() {
        log.info("create message = {}", message);
        return message;
    }
}
