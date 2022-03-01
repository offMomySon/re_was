package content.factory;

import config.easteregg.EasterEggInfo;
import content.message.Message;
import content.message.SimpleMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.easteregg.EasterEggRepository;
import util.Util;

import java.nio.file.Path;

class EasterEggMessageFactoryTest {

    //TODO
    // 계속해서 test 를 하기 위해서 실제 데이터인 url 과 테스트 suit 가 동일한 값으로 셋팅되어야 한다.
    @DisplayName("path 가 존재하면 true 값이 출력되야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test1", "/test2", "/test3"})
    void test1(String _path) {
        //given
        Path path = Util.normalizePath(Path.of(_path));

        EasterEggMessageFactory easterEggMessageFactory = new EasterEggMessageFactory(path);

        //when
        boolean actual = easterEggMessageFactory.isSupport();

        //then
        Assertions.assertThat(actual)
                .isTrue();
    }

    @DisplayName("path 가 존재하지 않으면 false 값이 출력되야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/not_exist_path"})
    void test2(String _path) {
        //given
        Path path = Util.normalizePath(Path.of(_path));

        EasterEggMessageFactory easterEggMessageFactory = new EasterEggMessageFactory(path);

        //when
        boolean actual = easterEggMessageFactory.isSupport();

        Assertions.assertThat(actual)
                .isFalse();
    }

    @DisplayName("path 가 존재하면 message 를 생성해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test1", "/test2", "/test3"})
    void test3(String _path) {
        //given
        Path path = Util.normalizePath(Path.of(_path));

        EasterEggMessageFactory easterEggMessageFactory = new EasterEggMessageFactory(path);

        //when
        Message actual = easterEggMessageFactory.createMessage();

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }


    @DisplayName("path 가 존재하지 않으면 message 생성시 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/not_exist_dir"})
    void test4(String _path) {
        //given
        Path path = Util.normalizePath(Path.of(_path));

        EasterEggMessageFactory easterEggMessageFactory = new EasterEggMessageFactory(path);

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggMessageFactory.createMessage());

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }


//    // test 에서 처음
//    // 1. method 에서 필요한 param 을 객체 외부에서 생성한다.
//    @DisplayName("easter egg message factory proto")
//    @Test
//    void proto() {
//        Path _target = Path.of("/target");
//
//        EasterEggMessageFactory easterEggMessageFactory = new EasterEggMessageFactory(_target) {
//            Path target = _target;
//
//            @Override
//            public Message createMessage() {
//                return super.createMessage();
//            }
//
//            @Override
//            public boolean isSupport() {
//                return super.isSupport();
//            }
//        };
//    }

}