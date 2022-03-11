package config.easteregg;

import config.easteregg.property.EasterEggProperty;
import config.easteregg.property.EasterEggTemplateProperty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


class EasterEggConfigTest {
    private static Stream<Arguments> provideType() {
        return Arrays.stream(EasterEggType.values())
                .map(EasterEggType::getValue)
                .map(it -> Arguments.of(it));
    }

    private static List<EasterEggProperty> createEasterEggInfo(String type) throws Exception {
        return List.of(EasterEggProperty.ofJackSon("/testURL", "testContent", type));
    }

    @DisplayName("EasterEggConfig 가 정상적으로 생성되어야 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideType")
    void test1(String type) throws Exception {
        //given
        List<EasterEggProperty> easterEggProperties = createEasterEggInfo(type);

        //when
        Throwable actual = Assertions.catchException(() -> EasterEggConfig.ofJackSon(easterEggProperties, List.of(new EasterEggTemplateProperty(type, "test format"))));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }
}