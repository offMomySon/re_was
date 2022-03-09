package config;

import config.easteregg.EasterEggConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringInfoConfigTest {
    @DisplayName("EasterEggInfo Config 자신의 object 를 성성합니다.")
    @Test
    void test4() {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> EasterEggConfig.create());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }
}