package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    By usernameInput = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"usernameInput\")"
    );

    By passwordInput = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"passwordInput\")"
    );

    By loginButton = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"loginButton\")"
    );

    By errorMessage = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"flashMessageText\")"
    );

    By usernameErrorText = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"usernameErrorText\")"
    );

    By passwordErrorText = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"passwordErrorText\")"
    );

    By usernameValidationError = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"usernameErrorText\")"
    );

    By inputValidationToast =
            AppiumBy.xpath("//android.widget.TextView[@text='INPUT_VALIDATION_ERROR']");

    public void enterUsername(String username) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput)
        ).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }

    public String getUsernameError() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameErrorText)
        ).getText();
    }

    public String getPasswordError() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordErrorText)
        ).getText();
    }

    public boolean isLoginButtonEnabled() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isEnabled();
    }

    public String getUsernameValidationError() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameValidationError
                )
        ).getText();
    }

    public String getPasswordValidationError() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordErrorText)
        ).getText();
    }

    public String getInputValidationToast() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        inputValidationToast
                )
        ).getText();
    }


}