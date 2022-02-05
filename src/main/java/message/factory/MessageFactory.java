package message.factory;

import message.Message;

// todo message 생성을 시스템화
public interface MessageFactory {
    Message createMessage();

    boolean isSupport();
}
