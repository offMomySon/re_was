package config.easteregg;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


class EasterEggInfoTest {

    @DisplayName("객체 생성시 url 이 상대경로를 벗어나면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/..", "/depth1/../..", "/depth1/depth2/../../.."})
    void test1(String url) {
        //given
        //when
        Throwable actual = Assertions.catchException(() -> EasterEggInfo.ofJackSon(url, "temp message", "default"));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("url 을 canonical value 로 가져와야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/t1/..", "/t1/t2/..", "/t1/t2/t3/../.."})
    void test2(String url) {
        //given
        EasterEggInfo easterEggMapInfo = EasterEggInfo.ofJackSon(url, "temp message", "default");

        Path expect = null;
        try {
            expect = new File(url).getCanonicalFile().toPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //when
        Path actual = easterEggMapInfo.getUrl();

        //then
        Assertions.assertThat(actual)
                .isEqualTo(expect);
    }

    @DisplayName("content 를 정상적으로 가져와야합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"first", "second", "third"})
    void test3(String content) {
        //given
        EasterEggInfo easterEggMapInfo = EasterEggInfo.ofJackSon("/temp", content, "default");

        //when
        String actual = easterEggMapInfo.getContent();

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isNotBlank()
                .isNotEmpty()
                .contains(content);
    }

    @DisplayName("대소문자에 상관없이, 유효한 EasterEggType 이면 정상적으로 생성합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"default", "Default", "DEFAULT", "event", "Event", "eVent", "special", "specIal", "SPECIAL"})
    void test4(String type) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> EasterEggInfo.ofJackSon("/", "content", type));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("EasterEggType 이 아닌 string 이 들어오면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"default ", "event ", "special ", "notType", "abcdType", "awsomeType"})
    void test5(String type) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> EasterEggInfo.ofJackSon("/", "content", type));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }
//
//    private static Stream<Arguments> provideTypeAndClazz() { // argument source method
//        return Stream.of(
//                Arguments.of("default", DefaultEasterEgg.class),
//                Arguments.of("event", EventEasterEgg.class),
//                Arguments.of("special", SpecialEasterEgg.class)
//        );
//    }
//
//    @DisplayName("EasterEggInfo type 에 따라 EasterEgg  로 변환 가능해야합니다.")
//    @ParameterizedTest
//    @MethodSource("provideTypeAndClazz")
//    void test6(String type, Class<EasterEgg> clazz) {
//        //given
//        EasterEggInfo easterEggInfo = EasterEggInfo.ofJackSon("/url", "content", type);
//
//        //when
//        EasterEgg actual = easterEggInfo.toEasterEgg();
//
//        //
//        Assertions.assertThat(actual)
//                .isNotNull()
//                .isInstanceOf(clazz);
//    }
}

