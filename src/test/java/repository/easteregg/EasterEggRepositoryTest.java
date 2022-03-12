package repository.easteregg;

import config.easteregg.EasterEgg;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class EasterEggRepositoryTest {
    // TODO
    // 데이터를 이렇게 설정하는게 맞나..?
    private static final int testDataCount = 3;

    private static List<Path> createPaths() {
        String tempPath = "/test/";
        List<Path> paths = IntStream.range(0, testDataCount)
                .mapToObj(it -> Paths.get(tempPath + it))
                .collect(Collectors.toList());

        return paths;
    }

    private static Stream<Arguments> provideExistPaths() {
        List<Path> existPaths = createPaths();

        return existPaths.stream()
                .map(it -> Arguments.of(it));
    }

    private static Stream<Arguments> provideNotExistPaths() {
        String notExist = "/notExist";
        List<Path> existPaths = createPaths();

        return existPaths.stream()
                .map(it -> it + notExist)
                .map(it -> Arguments.of(it));
    }

    private static List<String> createContents() {
        String tempContent = "content";
        List<String> contents = IntStream.range(0, testDataCount)
                .mapToObj(it -> tempContent + it)
                .collect(Collectors.toList());

        return contents;
    }

    private static Map<Path, EasterEgg> createEasterEggs() {
        List<Path> paths = createPaths();
        List<String> contents = createContents();

        Map<Path, EasterEgg> easterEggs = new HashMap<>();
        for (int i = 0; i < testDataCount; i++) {
            easterEggs.put(paths.get(i), new EasterEgg(paths.get(i), contents.get(i)));
        }

        return easterEggs;
    }

    private static EasterEggRepository createEasterEasterEggRepository() {
        return new EasterEggRepository(createEasterEggs());
    }

    @DisplayName("객체를 정상적으로 생성합니다.")
    @Test
    void test1() {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> createEasterEasterEggRepository());

        //then
        Assertions.assertThat(actual)
                .isNull();
    }

    // find
    @DisplayName("존재하는 path 이면 EaterEgg 가 존재한는 Optional 을 가져와야합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideExistPaths")
    void test2(Path path) {
        //given
        EasterEggRepository easterEasterEggRepository = createEasterEasterEggRepository();

        //when
        Optional<EasterEgg> actual = easterEasterEggRepository.find(path);

        //then
        Assertions.assertThat(actual)
                .isNotEmpty();
    }

    @DisplayName("존재하지 않는 path 이면 Empty Optional 을 가져와야합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideNotExistPaths")
    void test3(Path path) {
        //given
        EasterEggRepository easterEasterEggRepository = createEasterEasterEggRepository();

        //when
        Optional<EasterEgg> actual = easterEasterEggRepository.find(path);

        System.out.println(actual);

        //then
        Assertions.assertThat(actual)
                .isEmpty();
    }

    @DisplayName("존재하는 path 이면 true 를 가져와야합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideExistPaths")
    void test4(Path path) {
        //given
        EasterEggRepository easterEasterEggRepository = createEasterEasterEggRepository();

        //when
        Boolean actual = easterEasterEggRepository.isExist(path);

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isTrue();
    }

    @DisplayName("존재하지 않는 path 이면 false 을 가져와야합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideNotExistPaths")
    void test5(Path path) {
        //given
        EasterEggRepository easterEasterEggRepository = createEasterEasterEggRepository();

        //when
        Boolean actual = easterEasterEggRepository.isExist(path);

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isFalse();
    }

}