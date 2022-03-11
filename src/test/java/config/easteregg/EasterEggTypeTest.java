package config.easteregg;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

class EasterEggTypeTest {
    private static Stream<Arguments> provideRandomPickDiffLetterCase() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        Function<String, String> randomLetterCreator = value -> {
            int pickIdx = random.nextInt(value.length());
            boolean isUpperCase = random.nextBoolean();

            for (int i = 0; i < value.length(); i++) {
                if (i == pickIdx) {
                    sb.append(isUpperCase ? Character.toLowerCase(value.charAt(i)) : Character.toUpperCase(value.charAt(i)));
                    continue;
                }
                sb.append(isUpperCase ? Character.toUpperCase(value.charAt(i)) : Character.toLowerCase(value.charAt(i)));
            }

            String result = sb.toString();
            sb.setLength(0);

            return result;
        };

        return Arrays.stream(EasterEggType.values())
                .flatMap(it -> Stream.of(it, it, it))
                .map(EasterEggType::getValue)
                .map(randomLetterCreator)
                .map(it -> Arguments.of(it));
    }

    private static Stream<Arguments> provideNotWork() {
        return Arrays.stream(EasterEggType.values())
                .map(EasterEggType::getValue)
                .map(it -> it + " not work")
                .map(Arguments::of);
    }

    @DisplayName("EasterEggType 이 아닌 string 이 들어오면 exception 이 발생합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideNotWork")
    void test1(String value) {
        //given
        //when
        Throwable actual = Assertions.catchException(() -> EasterEggType.parse(value));

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("대소문자에 상관없이, 유효한 EasterEggType 이면 정상적으로 생성합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideRandomPickDiffLetterCase")
    void test2(String value) {
        //given
        //when
        EasterEggType actual = EasterEggType.parse(value);

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }
}