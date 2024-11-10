package booking.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class WaitUtils {

	public AndroidDriver <MobileElement> driver;


    public WaitUtils(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }
	
    // Wait for element visibility
    public void waitForElementVisible(MobileElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be clickable
    public void waitForElementClickable(MobileElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element to be invisible
    public void waitForElementInvisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for element to be present in DOM
    public void waitForElementPresent(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Fluent wait for an element with custom polling and timeout
    public WebElement fluentWait(final By locator, int timeoutInSeconds, int pollingInMillis) {
        FluentWait<AndroidDriver <MobileElement>> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(timeoutInSeconds))
            .pollingEvery(Duration.ofMillis(pollingInMillis))
            .ignoring(Exception.class);

        return wait.until(new Function<AndroidDriver <MobileElement>, WebElement>() {
            public WebElement apply(AndroidDriver <MobileElement> driver) {
                return driver.findElement(locator);
            }
        });
    }

    // Implicit wait setup
    public void setImplicitWait(int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }

    // Wait until text is present in a specific element
    public void waitForTextToBePresentInElement(MobileElement element, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // Wait until an element is enabled
    public void waitForElementEnabled(MobileElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait until a specific attribute is present in an element
    public void waitForAttribute(MobileElement element, String attribute, String value, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

}
