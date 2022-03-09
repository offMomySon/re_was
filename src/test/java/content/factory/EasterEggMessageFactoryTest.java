package content.factory;

import content.message.Message;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.easteregg.EasterEggRepository;
import util.Util;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

class EasterEggMessageFactoryTest {

    private static EasterEggRepository createRepository(Map<Path, String> map) throws Exception {
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

        EasterEggRepository easterEggRepository = createRepository(Map.of(repositoryPath, "content"));
        EasterEggMessageFactory easterEggMessageFactory = createFactory(factoryPath, easterEggRepository);

        return easterEggMessageFactory;
    }


    @Test
    void test2() throws Exception {
        EasterEggRepository content = createRepository(Map.of(Paths.get("/temp"), "content"));
        System.out.println("temp");
    }

    //    //TODO 1
//    // 계속해서 test 를 하기 위해서 실제 데이터인 url 과 테스트 suit 가 동일한 값으로 셋팅되어야 한다.
    @DisplayName("path 가 존재하면 true 값이 출력되야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test1", "/test2", "/test3"})
    void test1(String _path) throws Exception {
        //given
        EasterEggMessageFactory easterEggMessageFactory = createBy(_path);
//                EasterEggMessageFactory easterEggMessageFactory = new EasterEggMessageFactory(path);

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

        Assertions.assertThat(actual)
                .isFalse();
    }

    //
    @DisplayName("easter egg 의 path 이면 message 를 생성해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/test1", "/test2", "/test3"})
    void test3(String _path) throws Exception {
        //given
        EasterEggMessageFactory easterEggMessageFactory = createBy(_path);

        //when
        Message actual = easterEggMessageFactory.createMessage();

        //then
        Assertions.assertThat(actual)
                .isNotNull();
    }

    //
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
//

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