package util;

import config.download.DownloadConfig;
import config.entrypoint.EntryPointConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Path;

class UtilTest {

    //TODO
    // file path 를 row data 로 가져와야하는게 너무 지엽적인 테스티 인것 같다.
    // 1. Config class 에서 가져오거나 ( public open 해야함. ) - 테케를 위해서 코드 수정되서 안좋다.
    // 2. 아니면 지금 테케 처럼 데이터 셋에 파일의 실제 데이터를 박아넣어야 할 것 같다. - 아니면 검증할 방법이 없는데?

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