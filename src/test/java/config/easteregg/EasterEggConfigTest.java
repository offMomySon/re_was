package config.easteregg;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// TODO
// 단순 get 을 테스트할 필요가 있을까?
// 나중에 로직이 추가되어서 복작하게 get 을 가져오는 것을 고려하면 get 도 테스트 하는게 맞아보인다.
class EasterEggConfigTest {
    private static EasterEggConfig createEasterEggConfigFromFile() {
        return EasterEggConfig.create();
    }

    @DisplayName("config 파일을 통해서 정상적으로 객체가 생성되어야 합니다.")
    @Test
    void test0() {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> createEasterEggConfigFromFile());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("EasterEggProperty 를 정상적으로 가져옵니다.")
    @Test
    void test1() {
        //given
        EasterEggConfig easterEggConfig = createEasterEggConfigFromFile();

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggConfig.getEasterEggProperties());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("EasterEggTemplateProperty 를 정상적으로 가져옵니다.")
    @Test
    void test2() {
        //given
        EasterEggConfig easterEggConfig = createEasterEggConfigFromFile();

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggConfig.getEasterEggTemplateProperties());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }
}