package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class OnboardingPage extends BasePage {

    public OnboardingPage(AndroidDriver driver) {
        super(driver);
    }

    By nextArrow = By.xpath("//android.widget.TextView[@text='']");

    By finishButton = By.xpath(
            "//android.view.ViewGroup[@content-desc='დასრულება']"
    );

    public void clickNext() {
        wait.until(
                ExpectedConditions.elementToBeClickable(nextArrow)
        ).click();
    }

    public void clickFinish() {
        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public void completeOnboarding() {

        for (int i = 0; i < 3; i++) {
            clickNext();
        }

        clickFinish();
    }
}