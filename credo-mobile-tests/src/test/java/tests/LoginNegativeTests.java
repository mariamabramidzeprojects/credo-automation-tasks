package tests;

import pages.LoginPage;
import base.BaseTest;
import pages.OnboardingPage;
import pages.LanguagePage;
import org.testng.annotations.DataProvider;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginNegativeTests extends BaseTest {

    @DataProvider(name = "Languages")
    public Object[][] languages() {
        return new Object[][]{
                {"KA", "ავტორიზაცია ვერ ხერხდება, შეიყვანეთ მონაცემები სწორად"},
                {"EN", "Authentication failed, enter data correctly"},
                {"RU", "Авторизация не удалась, введите данные правильно"}
        };
    }

    @DataProvider(name = "invalidUsernameLanguages")
    public Object[][] invalidUsernameLanguages() {
        return new Object[][]{
                {"KA", "მომხმარებლის სახელში გამოყენებულია არასწორი სიმბოლოები"},
                {"EN", "Wrong symbols"},
                {"RU", "В имени пользователя использованы недопустимые символы"}
        };
    }

    @Test(dataProvider = "Languages")
    public void loginWithInvalidCredentials(String language, String expectedError) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);
        LanguagePage languagePage = new LanguagePage(driver);

        System.out.println("App successfully opened.");
        System.out.println("Current language: " + language);

        onboardingPage.completeOnboarding();
        languagePage.selectLanguage(language);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().resourceId(\"loginButton\")"
                )
        ));

        loginPage.enterUsername("wronguser");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                expectedError
        );
    }

    @Test
    public void loginWithEmptyFields() {

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        onboardingPage.completeOnboarding();

        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getUsernameError(),
                "აუცილებელი ველი არ არის შევსებული"
        );

        Assert.assertEquals(
                loginPage.getPasswordError(),
                "აუცილებელი ველი არ არის შევსებული"
        );
    }

    @Test
    public void loginWithEmptyUsername() {

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        onboardingPage.completeOnboarding();

        loginPage.enterPassword("123456");

        Assert.assertFalse(
                loginPage.isLoginButtonEnabled()
        );
    }

    @Test
    public void loginWithEmptyPassword() {

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        onboardingPage.completeOnboarding();

        loginPage.enterUsername("test");

        Assert.assertFalse(
                loginPage.isLoginButtonEnabled()
        );
    }

    @Test
    public void loginWithInvalidUsernameSymbols() {

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        onboardingPage.completeOnboarding();

        loginPage.enterUsername("!!!!!!");
        loginPage.enterPassword("123456");

        Assert.assertEquals(
                loginPage.getUsernameValidationError(),
                "მომხმარებლის სახელში გამოყენებულია არასწორი სიმბოლოები"
        );

        Assert.assertFalse(
                loginPage.isLoginButtonEnabled()
        );
    }

    @Test
    public void loginWithPasswordSpaces() {

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        onboardingPage.completeOnboarding();

        loginPage.enterUsername("testuser");
        loginPage.enterPassword("     ");

        Assert.assertEquals(
                loginPage.getPasswordValidationError(),
                "შეიყვანეთ მხოლოდ დასაშვები სიმბოლოები"
        );

        Assert.assertFalse(
                loginPage.isLoginButtonEnabled()
        );
    }

    @Test
    public void loginWithLongUsername() {

        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        onboardingPage.completeOnboarding();

        loginPage.enterUsername(
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        );

        loginPage.enterPassword("123");

        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getInputValidationToast(),
                "INPUT_VALIDATION_ERROR"
        );
    }
}