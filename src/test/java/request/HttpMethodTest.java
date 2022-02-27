package request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HttpMethodTest {

    @DisplayName("존재하지 않는 http method 메서드를 받으면 exception 이 발생합니다.")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"UNKNOWN_METHOD", "NOT USABLE METHOD", "POSTS", "GETS"})
    void test1(String method) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> HttpMethod.parse(method));

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isInstanceOf(HttpMethodException.class);
    }

    @DisplayName("일치하는 메서드가 있으면 정상적으로 객체를 생성합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"get", "Get", "Post", "POST", "Put", "PUT", "delete", "option", "head"})
    void test2(String method) {
        //given
        //when
        HttpMethod actual = HttpMethod.parse(method);

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void test3(String value) {
        System.out.println("'" + value + "'");

    }
}