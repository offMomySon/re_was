package config.easteregg;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


class EasterEggMapInfoTest {

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

        //TODO
        // 이 테스트는 getUrl 이 canonical 값을 전달하기 위한 테스트.
        // 그래서 getCanonicalFile 메서드를 사용함.
        // 다른 방법으로 테스트 고민해본 것은, Path.normalizePath 를 사용해서 동일한지 비교하는 방법.
        // EasterEggMapInfo 의 url 에 저장되어있는 url 은
        // Path.normalizePath 를 거치고 데이터가 저장 되기 때문에 같은 로직을 타는 테스트가 되서 효용성이 없다고 생각되는데.
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
                .isNotEmpty();
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
}

