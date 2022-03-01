package repository.easteregg;

import config.easteregg.EasterEggInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Util;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class EasterEggRepositoryTest {

    // TODO
    // easter_egg.json 의 데이터와 find 를 검증하기 위한 데이터가 일치해야한다.
    // 데이터를 파편화 할 수 밖에 없다. -> 어떻게 하지?
    // 1. 코드로 가져오는 방법.
    @DisplayName("path 에 해당하는 easter egg 가 찾으면 가져옵니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test1", "/test1", "/test3"})
    void test1(String _path) {
        //given
        EasterEggRepository easterEggRepository = EasterEggRepository.instance;

        Path path = Util.normalizePath(Path.of(_path));

        //when
        EasterEggInfo actual = easterEggRepository.find(path);

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isNotEqualTo(EasterEggInfo.EMPTY_EASTER_EGG);
    }

    @DisplayName("path 에 해당하는 easter egg 를 찾지 못하면, EMPTY easter egg 를 가져옵니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/not_exist_dir"})
    void test2(String _path) {
        //given
        EasterEggRepository easterEggRepository = EasterEggRepository.instance;

        Path path = Util.normalizePath(Path.of(_path));

        //when
        EasterEggInfo actual = easterEggRepository.find(path);

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isEqualTo(EasterEggInfo.EMPTY_EASTER_EGG);
    }

    @DisplayName("path 에 해당하는 easter egg 가 존재하면 true 값을 가져옵니다.")
    @ParameterizedTest
    @ValueSource(strings = "/test1")
    void test3(String _path) {
        //given
        EasterEggRepository easterEggRepository = EasterEggRepository.instance;

        Path path = Util.normalizePath(Path.of(_path));

        //when
        Boolean actual = easterEggRepository.isExist(path);

        //then
        Assertions.assertThat(actual)
                .isTrue();
    }

    @DisplayName("path 에 해당하는 easter egg 가 존재하지 않으면 false 값을 가져옵니다.")
    @ParameterizedTest
    @ValueSource(strings = "/not_exist_dir")
    void test4(String _path) {
        //given
        EasterEggRepository easterEggRepository = EasterEggRepository.instance;

        Path path = Util.normalizePath(Path.of(_path));

        //when
        Boolean actual = easterEggRepository.isExist(path);

        //then
        Assertions.assertThat(actual)
                .isFalse();
    }
}