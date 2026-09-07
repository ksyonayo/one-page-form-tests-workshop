import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
