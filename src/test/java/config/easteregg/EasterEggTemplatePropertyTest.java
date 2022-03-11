package config.easteregg;

import config.easteregg.property.EasterEggTemplateProperty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

class EasterEggTemplatePropertyTest {
    private static String provideType() {
        return EasterEggType.DEFAULT.getValue();
    }

    private static String provideFormat() {
        Random random = new Random();
        String formatter = "{%s}";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < random.nextInt(10); i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        sb.append(formatter);
        for (int i = 0; i < random.nextInt(10); i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }

        return sb.toString();
    }

    private static EasterEggTemplateProperty createEasterEggTemplateProperty() {
        return new EasterEggTemplateProperty(provideType(), provideFormat());
    }

    private static EasterEggTemplateProperty te() {
        return new EasterEggTemplateProperty(provideType(), "이것은 test easter egg!,");
    }


    @DisplayName("객체 생성시 format string 이 존재하지않은 format 이면 exception 이 발생합니다.")
    @Test
    void test0() {
        //given
        //when
        Throwable actual = Assertions.catchException(() -> new EasterEggTemplateProperty(provideType(), "이것은 test easter egg!,"));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("객체가 정상적으로 생성되어야 합니다.")
    @Test
    void test1() {
        //given
        //when
        Throwable actual = Assertions.catchException(() -> createEasterEggTemplateProperty());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("EasterEggType 을 정상적으로 가져와야 합니다.")
    @Test
    void test2() {
        //given
        EasterEggTemplateProperty easterEggTemplateProperty = createEasterEggTemplateProperty();

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggTemplateProperty.getEasterEggType());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("format 을 정상적으로 가져와야 합니다.")
    @Test
    void test3() {
        //given
        EasterEggTemplateProperty easterEggTemplateProperty = createEasterEggTemplateProperty();

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggTemplateProperty.getFormat());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }
}