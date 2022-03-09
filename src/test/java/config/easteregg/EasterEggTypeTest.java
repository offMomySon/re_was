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

    private static Stream<Arguments> provide() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        Function<String, String> randomLetterCaseCreator = value -> {
            int pickIdx = random.nextInt(value.length());
            boolean isUpperCase = random.nextBoolean();

            for (int i = 0; i < value.length(); i++) {
                if (pickIdx == i) {
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
                .map(EasterEggType::getValue)
                .flatMap(it -> Stream.of(it, it, it))
                .map(randomLetterCaseCreator)
                .map(Arguments::of);
    }

    private static Stream<Arguments> provideNotWork() {
        return Arrays.stream(EasterEggType.values())
                .map(EasterEggType::getValue)
                .map(it -> it + " not work")
                .map(Arguments::of);
    }

    @DisplayName("EasterEggType 이 아닌 string 이 들어오면 exception 이 발생합니다.")
    @ParameterizedTest
//    @ValueSource(strings = {"default ", "event ", "special ", "notType", "abcdType", "awsomeType"})
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
//    @ValueSource(strings = {"default", "Default", "DEFAULT", "event", "Event", "eVent", "special", "specIal", "SPECIAL"})
    @MethodSource(value = "provide")
    void test2(String value) {
        //given
        //when
        EasterEggType actual = EasterEggType.parse(value);

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }
//
//    @Test
//    void test3() {
//        EasterEgg easterEgg = EasterEggType.DEFAULT.toContent("defualt");
//
//    }


    // TODO 2
    //  enum 은 이렇게 프로토타입 만들면 되는지?
//    @Test
//    void proto() {
//
//        class EasterEggType2 {
//            private final String value;
//
//            public EasterEggType2(String value) {
//                this.value = value;
//            }
//
//            public EasterEggType2 parse(EasterEggType2[] arr, String type) {
//                return Arrays.stream(arr)
//                        .filter(it -> it.value.equalsIgnoreCase(type))
//                        .findFirst()
//                        .orElseThrow(() -> new RuntimeException("존재하는 타입이 없습니다."));
//            }
//
//        }
//
//        EasterEggType2 t1 = new EasterEggType2("default");
//        EasterEggType2 t2 = new EasterEggType2("special");
//        EasterEggType2 t3 = new EasterEggType2("event");
//
//        EasterEggType2[] tArr = new EasterEggType2[]{t1, t2, t3};
//
//
//        EasterEggType2 pasing = new EasterEggType2("");
//        EasterEggType2 special = pasing.parse(tArr, "special");
//    }
}