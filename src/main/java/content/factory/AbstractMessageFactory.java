package content.factory;

import content.message.Message;

// todo message 생성을 시스템화
public interface AbstractMessageFactory {
    Message createMessage();

    boolean isSupport();
}
