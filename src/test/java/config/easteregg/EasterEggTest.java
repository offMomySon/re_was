package config.easteregg;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class EasterEggTest {

    @DisplayName("객체 생성시 상대경로를 벗어난 url 을 받으면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/..", "/depth1/../..", "/depth1/depth2/../../.."})
    void test1(String url) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> new EasterEgg(Path.of(url), "content"));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("객체가 정상적으로 생성됩니다.")
    @Test
    void test2() {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> new EasterEgg(Path.of("/testURL"), "content"));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("url 을 canonical value 로 가져와야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/t1/..", "/t1/t2/..", "/t1/t2/t3/../.."})
    void test3(String url) {
        //given
        Path expect = null;
        try {
            expect = new File(url).getCanonicalFile().toPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EasterEgg easterEgg = new EasterEgg(Path.of(url), "content");

        //when
        Path actual = easterEgg.getURL();

        //then
        Assertions.assertThat(actual)
                .isEqualTo(expect);
    }

    @DisplayName("content 를 정상적으로 가져옵니다.")
    @ParameterizedTest
    @ValueSource(strings = {"content", "content2", "content3"})
    void test4(String content) {
        //given
        EasterEgg easterEgg = new EasterEgg(Path.of("/testURL"), content);

        //when
        String actual = easterEgg.getContent();

        //then
        Assertions.assertThat(actual)
                .isEqualTo(content);
    }
}