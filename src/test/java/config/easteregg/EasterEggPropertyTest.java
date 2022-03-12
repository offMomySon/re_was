package config.easteregg;

import config.easteregg.property.EasterEggProperty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class EasterEggPropertyTest {
    private static String provideType() {
        return EasterEggType.DEFAULT.getValue();
    }

    private static EasterEggProperty createEasterEggProperty() {
        return EasterEggProperty.ofJackSon("/temp", "content", provideType());
    }

    // TODO
    // 객체의 생성의 도메인적 부분이긴한데,
    // 내부적으로 util 속성을 함수가 역할 하는 것인데 얘도 검사해야할까?
    // 객체의 책임 관점에서 보면 해야하는게 맞는것 같다.
    @DisplayName("객체 생성시 url 이 상대경로를 벗어나면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/..", "/depth1/../..", "/depth1/depth2/../../.."})
    void test1(String url) {
        //given
        //when
        Throwable actual = Assertions.catchException(() -> EasterEggProperty.ofJackSon(url, "temp message", "default"));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("객체 생성시 빈 content 를 받으면 exception 이 발생합니다.")
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" "})
    void test1_1(String content) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> EasterEggProperty.ofJackSon("/temp", content, "default"));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("url 을 canonical value 로 가져와야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/t1/..", "/t1/t2/..", "/t1/t2/t3/../.."})
    void test2(String url) {
        //given
        EasterEggProperty easterEggMapInfo = EasterEggProperty.ofJackSon(url, "temp message", "default");

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

    // TODO
    //  null 도 발생안하고 자료형을 하나만 가져오는데 의미가 있나?..
    @Test
    @DisplayName("content 를 정상적으로 가져와야합니다.")
    void test3() {
        //given
        EasterEggProperty easterEggMapInfo = createEasterEggProperty();

        //when
        String actual = easterEggMapInfo.getContent();

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isNotBlank()
                .isNotEmpty();
    }

    @DisplayName("Path 를 정상적으로 가져와야합니다.")
    @Test
    void test4() {
        //given
        EasterEggProperty easterEggProperty = createEasterEggProperty();

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggProperty.getUrl());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("EasterEgg type 을 정상적으로 가져와야합니다.")
    @Test
    void test5() {
        //given
        EasterEggProperty easterEggMapInfo = createEasterEggProperty();

        //when
        EasterEggType actual = easterEggMapInfo.getType();

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }
}

