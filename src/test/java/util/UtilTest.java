package util;

import config.download.DownloadConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Path;

class UtilTest {

    @DisplayName("파일을 정상적으로 class 파일로 읽어 옵니다.")
    @ParameterizedTest
    @ValueSource(strings = {"src/main/resources/config/download.json"})
    void test1(String filePath) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> Util.createConfig(filePath, DownloadConfig.class));

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    @DisplayName("존재하는 파일이 아니면 exception 이 발생합니다")
    @ParameterizedTest
    @ValueSource(strings = {"notExistFilePath", "notExistFile.txt"})
    void test2(String filePath) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> Util.createConfig(filePath, DownloadConfig.class));

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isInstanceOf(ConfigException.class);
    }

    @DisplayName("상대 경로를 벗어난 class 를 읽으면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {""})
    void test3(String filePath) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> Util.createConfig(filePath, DownloadConfig.class));

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isInstanceOf(ConfigException.class);
    }

    @DisplayName("Path 가 상대경로를 벚어나면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/..", "/depth1/../../target", "/depth1/depth2/../../../target"})
    void test4(String file) {
        //given
        Path path = Path.of(file);

        //when
        Throwable actual = Assertions.catchThrowable(() -> Util.normalizePath(path));

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isInstanceOf(PathException.class);
    }
}