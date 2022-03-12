package content.factory;

import config.easteregg.EasterEgg;
import content.message.Message;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.easteregg.EasterEggRepository;
import util.Util;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.Map;

class EasterEggMessageFactoryTest {

    private static EasterEggRepository createRepository(Map<Path, EasterEgg> map) throws Exception {
        Constructor<EasterEggRepository> constructor = EasterEggRepository.class.getDeclaredConstructor(Map.class);
        constructor.setAccessible(true);

        EasterEggRepository easterEggRepository = constructor.newInstance(map);
        return easterEggRepository;
    }

    private static EasterEggMessageFactory createFactory(Path path, EasterEggRepository easterEggRepository) throws Exception {

        Constructor<EasterEggMessageFactory> constructor = EasterEggMessageFactory.class.getDeclaredConstructor(Path.class, EasterEggRepository.class);
        constructor.setAccessible(true);

        EasterEggMessageFactory easterEggMessageFactory = constructor.newInstance(path, easterEggRepository);
        return easterEggMessageFactory;
    }

    private static EasterEggMessageFactory createBy(String _path) throws Exception {
        return createBy(_path, _path);
    }

    private static EasterEggMessageFactory createBy(String _factoryPath, String _repositoryPath) throws Exception {
        Path factoryPath = Util.normalizePath(Path.of(_factoryPath));
        Path repositoryPath = Util.normalizePath(Path.of(_repositoryPath));

        EasterEggRepository easterEggRepository = createRepository(Map.of(repositoryPath, new EasterEgg(repositoryPath, "content")));
        EasterEggMessageFactory easterEggMessageFactory = createFactory(factoryPath, easterEggRepository);

        return easterEggMessageFactory;
    }

    @DisplayName("path 가 존재하면 true 값이 출력되야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test1", "/test2", "/test3"})
    void test1(String _path) throws Exception {
        //given
        EasterEggMessageFactory easterEggMessageFactory = createBy(_path);

        //when
        boolean actual = easterEggMessageFactory.isSupport();

        //then
        Assertions.assertThat(actual)
                .isTrue();
    }

    @DisplayName("easter egg 의 path 가 아니면, false 값이 출력되야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/not_exist_path"})
    void test2(String _path) throws Exception {
        //given
        EasterEggMessageFactory easterEggMessageFactory = createBy(_path, _path + "diff");

        //when
        boolean actual = easterEggMessageFactory.isSupport();

        //then
        Assertions.assertThat(actual)
                .isFalse();
    }

    @DisplayName("easter egg 의 path 이면 message 를 생성해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test", "/test2", "/test3"})
    void test3(String _path) throws Exception {
        //given
        EasterEggMessageFactory easterEggMessageFactory = createBy(_path);

        //when
        Message actual = easterEggMessageFactory.createMessage();

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    @DisplayName("easter egg 의 path 가 아니면 exception 이 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/not_exist_dir"})
    void test4(String _path) throws Exception {
        //given
        EasterEggMessageFactory easterEggMessageFactory = createBy(_path, _path + "diff");

        //when
        Throwable actual = Assertions.catchThrowable(() -> easterEggMessageFactory.createMessage());

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }
}