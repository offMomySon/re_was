package config.easteregg;

import config.easteregg.property.EasterEggTemplateProperty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class EasterEggTemplatePropertyTest {
    private static Stream<Arguments> provideType() {
        return Arrays.stream(EasterEggType.values())
                .map(EasterEggType::getValue)
                .map(it -> Arguments.of(it));
    }

    @DisplayName("객체가 정상적으로 생성되어야 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideType")
    void test(String type) {
        //given
        //when
        Throwable actual = Assertions.catchException(() -> new EasterEggTemplateProperty(type, "이것은 test easter egg!, {%s}"));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

}