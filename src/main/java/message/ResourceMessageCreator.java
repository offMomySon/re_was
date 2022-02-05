package message;

import config.DownloadConfig;
import config.EntryPointConfig;
import config.ThreadConfig;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

//todo - ResourceMessageJudge 는 왜 되지 못하는가? ( if 문으로 파일/디렉토리 판단만 해주면 되지 않는가? )
//todo - message 생성의 역할이 factory 가 아닌 creator 가 가지지 않는가?
//todo - path 로 부터 message 를 생성하는 도메인 객체. http 도메인과 message 도에인을 이어주는 역할?
@Slf4j
public class ResourceMessageCreator {
    private final Path target;
    private final Path welcomePage;

    private ResourceMessageCreator(@NonNull Path target, @NonNull Path welcomePage) {
        this.target = target;
        this.welcomePage = welcomePage;

        log.info("target = {}", this.target);
        log.info("welcomePage = {}", this.welcomePage);
    }

    public static ResourceMessageCreator from(@NonNull Path downloadDir, @NonNull Path welcomePage, @NonNull Path target) {
        target = Paths.get(downloadDir.toString(), target.toString());
        welcomePage = Paths.get(downloadDir.toString(), welcomePage.toString());
        return new ResourceMessageCreator(target, welcomePage);
    }

    // todo - optional 말고 다른 EMPTY_MESSAGE 주는게 더 좋지않나?
    public Optional<Message> create() {
        if (target.equals(welcomePage)) {
            return Optional.of(new WelcomePageMessage(DownloadConfig.getInstance(), EntryPointConfig.getInstance(), ThreadConfig.getInstance()));
        }
        if (Files.isDirectory(target)) {
            return Optional.of(DirectoryMessage.from(target));
        }
        if (Files.exists(target)) {
            return Optional.of(FileMessage.from(target));
        }

        return Optional.empty();
    }
}
