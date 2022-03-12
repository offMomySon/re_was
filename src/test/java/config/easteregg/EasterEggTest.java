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
    @DisplayName("객체가 정상적으로 생성됩니다.")
    @Test
    void test0() {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> new EasterEgg(Path.of("/testURL"), "content"));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }
    
    @DisplayName("path 을 canonical value 로 가져와야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/t1/..", "/t1/t2/..", "/t1/t2/t3/../.."})
    void test2(String url) {
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

    @DisplayName("path 를 정상적으로 가져옵니다.")
    @Test
    void test3() {
        //given
        EasterEgg easterEgg = new EasterEgg(Path.of("/testURL"), "content");

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEgg.getURL());

        //then
        Assertions.assertThat(actual)
                .isNull();
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