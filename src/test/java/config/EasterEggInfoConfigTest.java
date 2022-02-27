package config;

import config.easteregg.EasterEggConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EasterEggInfoConfigTest {

    @DisplayName("default message 를 가져옵니다.")
    @Test
    void test1() {
        //given
        EasterEggConfig easterEggConfig = new EasterEggConfig();

        //when
        String actual = easterEggConfig.getDefaultMessage();

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isNotBlank()
                .isNotEmpty();
    }

    @DisplayName("delicious easter egg message 를 가져옵니다.")
    @Test
    void test2() {
        //given
        EasterEggConfig easterEggConfig = new EasterEggConfig();

        //when
        String actual = easterEggConfig.getDeliciousEggMessage();

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .isNotBlank();
    }

    @DisplayName("special easter egg message 를 가져옵니다.")
    @Test
    void test3() {
        //given
        EasterEggConfig easterEggConfig = new EasterEggConfig();

        //when
        String actual = easterEggConfig.getSpecialEasterEggMessage();

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .isNotBlank();
    }
}