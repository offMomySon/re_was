package content.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EasterEggInfoMessageTest {

    @DisplayName("이스터에그 메세지를 생성합니다.")
    @Test
    void test() {
        //given
        String urlParam = "aaa";

        //when
        Throwable actual = Assertions.catchThrowable(() -> new EasterEggMessage(urlParam));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("맵핑되지 않는 url 은 기본 메세지를 생성합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"aaaa", "bbbb", "ccc"})
    void test2(String url) {
        //given
        EasterEggMessage easterEggMessage = new EasterEggMessage(url);

        //when
        String actual = easterEggMessage.create();

        //then
        Assertions.assertThat(actual)
                .isEqualTo("default 이스터 에그 메세지입니다.");
    }


    @Test
    void make() {
        String url = "aaa";

        Message easterEggMessage = new EasterEggMessage(url) {

            @Override
            public String create() {
                if (url.equals("aaa")) {
                    return "맛있는 부활절 계란.";
                } else if (url.equals("bbb")) {
                    return "이건 이스터 에그입니다.";
                }

                return "default 이스터 에그 메세지입니다.";
            }
        };
    }

}