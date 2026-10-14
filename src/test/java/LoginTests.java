import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests extends TestBase {

    @Test
    public void successfulAuthorization() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    // @Disabled("Auth by Press Enter is not implemented yet")
    public void successfulAuthorizationWithPressEnter() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    public void wrongPasswordAuthorizationTest() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("wrongpassword");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    public void wrongCredentialsAuthorizationTest() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("user123");
        $("[data-testid=password-input]").setValue("wrongpassword");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    public void tooShortLoginAuthorizationTest() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("us");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Login must be at least 3 characters"));
    }

    @Test
    public void tooShortPasswordAuthorizationTest() {
        open("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("pas");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Password must be at least 6 characters"));
    }

    @Test
    public void emptyPasswordAuthorizationTest() {
        open("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Password is required (minimum 6 characters)"));
    }

        @Test
    public void emptyFormAuthorizationTest() {
        open ("/login.html");

        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Login and password are required (minimum 3 and 6 characters)"));
    }

    @Test
    public void titleVisibilityAuthorizationTest() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=logged-in-title]").shouldBe(visible);
    }

    @Test
    public void LogoutButtonVisibilityAuthorizationTest() {
        open ("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=logout-button]").shouldBe(visible);
    }

}
