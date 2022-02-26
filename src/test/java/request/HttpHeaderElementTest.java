package request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HttpHeaderElementTest {

    //구성
    @DisplayName("중복값 체크")
    @ParameterizedTest
    @ValueSource(strings = {"value_1", "value_1,value_1", "value_1,value_2,value_2"})
    void test1(String value) {
        testValues(value);
    }

    @DisplayName("value 값에 공백이 존재하면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"value1, value2", "value1,    value2", "value1,value2 ", " value1,value2"})
    void test2_1(String _values) {
        //given
        //when
        Throwable throwable = Assertions.catchThrowable(() -> HttpHeaderElement.from("key1:" + _values));

        //then
        Assertions.assertThat(throwable)
                .isNotNull()
                .isInstanceOf(HttpHeaderElementException.class);
    }

    //값 조회
    @DisplayName("모든 value 들을 가져와야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"value1", "value1,value2", "value1,value2,value3", "value1,value2,value3,value4"})
    void test2(String _values) {
        testValues(_values);
    }

    @DisplayName("유효하지 않은 라인을 넣었을시 생성시 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"KeyAbsent,delimeter", ":keyLen0", "key hasSpace:value1", "   :KeyBlank",
            "AbsentValue:", "ValueLen0:,value1", "ValueBlank:      ,value1"})
    void test3(String line) {
        Throwable throwable = Assertions.catchThrowable(() -> HttpHeaderElement.from(line));

        Assertions.assertThat(throwable)
                .isNotNull()
                .isInstanceOf(HttpHeaderElementException.class);
    }

    @DisplayName("하나의 value 값 가져온다.")
    @ParameterizedTest
    @ValueSource(strings = {"value1", "value1,value2"})
    void test4(String value) {
        // given
        Set<String> expectCandidates = Set.of(value.split(","));

        HttpHeaderElement httpHeaderElement = new HttpHeaderElement("key1", expectCandidates);

        // when
        String actual = httpHeaderElement.getAnyValue();

        //then
        Assertions.assertThat(actual).containsAnyOf(value.split(","));
    }

    private static void testValues(String values) {
        //given
        HttpHeaderElement httpHeaderElement = HttpHeaderElement.from("key:" + values);

        Set<String> expect = new HashSet<String>(Arrays.asList(values.split(",")));

        //when
        Set<String> actual = httpHeaderElement.getValues();

        //then
        Assertions.assertThat(actual).isEqualTo(expect);
    }
}