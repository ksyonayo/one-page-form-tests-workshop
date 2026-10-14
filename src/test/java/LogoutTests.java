import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LogoutTests extends TestBase {

    @Test
    //@Disabled("Bad practice - long e2e test, split to atomic tests")
    public void successfulLogout() {
        open ("/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));

        $("[data-testid=logout-button]").click();

        $("[data-testid=success-panel]").shouldNotBe(visible);
        $("[data-testid=login-form-title]").shouldHave(text("Login Form"));
    }

    @Test
    public void successfulLogoutWithLocalStorageAuth() {
        open ("/login.html");
        localStorage().setItem("authUser", "user1");
        open ("/login.html");

        $("[data-testid=logout-button]").click();

        $("[data-testid=success-panel]").shouldNotBe(visible);
        $("[data-testid=login-form-title]").shouldHave(text("Login Form"));
    }
}
