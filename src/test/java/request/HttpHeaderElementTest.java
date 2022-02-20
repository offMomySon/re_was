package request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HttpHeaderElementTest {

    //구성
    @DisplayName("중복값 체크")
    @ParameterizedTest
    @ValueSource(strings = {"value_1", "value_1, value_1", "value_1,value_2,value_2"})
    void test1(String value) {
        testValues(value);
    }

    //값 조회
    @DisplayName("value 조회")
    @ParameterizedTest
    @ValueSource(strings = {"value1", "value1,value2"})
    void test2(String _values) {
        testValues(_values);
    }

    @DisplayName("유효하지 않은 라인을 넣었을시 생성시 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"sadfasdf"})
    void test3(String line) {
        Throwable throwable = Assertions.catchThrowable(() -> HttpHeaderElement.from(line));

        Assertions.assertThat(throwable).isNotNull();
    }

    private static void testValues(String values) {
        //given
        HttpHeaderElement httpHeaderElement = HttpHeaderElement.from("key:" + values);

        Set<String> expect = Set.of(values.split(","));

        //when
        Set<String> actual = httpHeaderElement.getValues();

        //then
        Assertions.assertThat(actual).isEqualTo(expect);
    }
}