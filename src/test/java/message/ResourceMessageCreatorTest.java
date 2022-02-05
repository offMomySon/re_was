package message;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ResourceMessageCreatorTest {

    @Test
    void create() {
        ResourceMessageCreator resourceMessageCreator = ResourceMessageCreator.from(Paths.get("TestDir"), Paths.get("welcomePage"), Paths.get("Open"));
    }
}