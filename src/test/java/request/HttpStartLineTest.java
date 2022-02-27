package request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HttpStartLineTest {

    @DisplayName("startLine 이 3개의 파트가 아니면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"GET /twoParts", "GET /getFour HTTP/1.1 nothing", "nothing GET /search HTTP/1.1", "GET nothing /search HTTP/1.1"})
    void test1(String startLine) {
        //given
        //when
//        Assertions.assertThatExceptionOfType(HttpStartLineException.class).isThrownBy(() -> HttpStartLine.create(startLine));
        Throwable actual = Assertions.catchThrowable(() -> HttpStartLine.create(startLine));

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isInstanceOf(HttpStartLineException.class);
    }

    @DisplayName("상대 경로를 벗어나면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"GETS /get HTTP/1.1", "GET /getFo/../../..ur HTTP/1.1"})
    void test2(String startLine) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> {
            HttpStartLine.create(startLine);
        });

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isInstanceOf(HttpStartLineException.class);
    }

    @DisplayName("잘됨")
    @ParameterizedTest
    @ValueSource(strings = {"GET /test HTTP/1.1"})
    void test3(String startLine) {
        //given
        //when
        Throwable throwable = Assertions.catchThrowable(() -> HttpStartLine.create(startLine));

        //then
        Assertions.assertThat(throwable)
                .isNull();
    }
}