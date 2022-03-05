package config.easteregg;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EasterEggTypeTest {

    @DisplayName("EasterEggType 이 아닌 string 이 들어오면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"default ", "event ", "special ", "notType", "abcdType", "awsomeType"})
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
    @ValueSource(strings = {"default", "Default", "DEFAULT", "event", "Event", "eVent", "special", "specIal", "SPECIAL"})
    void test2(String value) {
        //given
        //when
        EasterEggType actual = EasterEggType.parse(value);

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    // TODO
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