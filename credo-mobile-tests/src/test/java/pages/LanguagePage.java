package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LanguagePage extends BasePage {

    public LanguagePage(AndroidDriver driver) {
        super(driver);
    }

    private final By georgianLanguage =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"ქართული\")"
            );

    private final By englishLanguage =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"English\")"
            );

    private final By russianLanguage =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Русский\")"
            );

    public void selectLanguage(String language) {

        try {
            driver.executeScript(
                    "mobile: clickGesture",
                    java.util.Map.of(
                            "x", 985,
                            "y", 190
                    )
            );

            Thread.sleep(2000);

            System.out.println("Language button clicked");

        } catch (Exception e) {
            throw new RuntimeException("Failed to open language menu", e);
        }

        switch (language) {

            case "KA":
                wait.until(
                        ExpectedConditions.elementToBeClickable(georgianLanguage)
                ).click();
                break;

            case "EN":
                wait.until(
                        ExpectedConditions.elementToBeClickable(englishLanguage)
                ).click();
                break;

            case "RU":
                wait.until(
                        ExpectedConditions.elementToBeClickable(russianLanguage)
                ).click();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported language: " + language
                );
        }

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("")
                )
        ).click();
    }
}