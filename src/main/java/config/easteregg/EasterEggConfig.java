package config.easteregg;

public class EasterEggConfig {
    private static final String DEFAULT_MESSAGE = "default 이스터 에그 메세지입니다.";
    private static final String DELICIOUS_EASTER_EGG_MESSAGE = "맛있는 부활절 계란.";
    private static final String SPECIAL_EASTER_EGG_MESSAGE = "특별한 이스터 에그 입니다.";

    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

    public String getDeliciousEggMessage() {
        return DELICIOUS_EASTER_EGG_MESSAGE;
    }

    public String getSpecialEasterEggMessage() {
        return SPECIAL_EASTER_EGG_MESSAGE;
    }
}
