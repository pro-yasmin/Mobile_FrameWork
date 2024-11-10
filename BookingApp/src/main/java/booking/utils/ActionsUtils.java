package booking.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class ActionsUtils extends Base  {


    public ActionsUtils() {
    	   PageFactory.initElements(driver, this);
    }

    // Tap on a mobile element
    public void tap(MobileElement element) {
        new TouchAction<>(driver)
            .tap(ElementOption.element(element))
            .perform(); 
    }

    // Long press on a mobile element
    public void longPress(MobileElement element, int durationInSeconds) {
        new TouchAction<>(driver)
            .longPress(LongPressOptions.longPressOptions()
            .withElement(ElementOption.element(element))
            .withDuration(Duration.ofSeconds(durationInSeconds)))
            .release()
            .perform();
    }

    // Swipe from one point to another
    public static void swipe(int startX, int startY, int endX, int endY, int durationInMillis) {
        new TouchAction<>(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMillis)))
            .moveTo(PointOption.point(endX, endY))
            .release()
            .perform();
    }

      // Swipe down (useful for refreshing lists)
    public static void swipeDown() {
        Dimension dimension = driver.manage().window().getSize();
        int startX = dimension.width / 2;
        int startY = (int) (dimension.height * 0.2);
        int endY = (int) (dimension.height * 0.8);

        swipe(startX, startY, startX, endY, 5000);
    }
    
    public static void swipeFromMiddleVerticallyWithWait() {

        // Get screen dimensions
        Dimension dimension = driver.manage().window().getSize();
        //Starting y location set to 80% of the height (near bottom)
        int startx = (int) (dimension.height * 0.70);
        //Ending y location set to 20% of the height (near top)
        int endy = (int) (dimension.height * 0.30);
        //x position set to mid-screen horizontally
        int starty = (int) dimension.width / 2;

     // Log screen dimensions and coordinates for debugging
        System.out.println("Screen width: " + dimension.width);
        System.out.println("Screen height: " + dimension.height);
        System.out.println("Start coordinates: (" + startx + ", " + starty + ")");
       // System.out.println("End coordinates: (" + startx + ", " + endY + ")");
       
        // Perform swipe action with a wait
           new TouchAction<>(driver)
            .press(PointOption.point(startx, starty))  // Start from the middle
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))) // Add a wait duration
            .moveTo(PointOption.point(startx, endy))    // Move downwards
            .release()
            .perform();
    }

    
	
    // Swipe up (useful for scrolling)
    public void swipeUp() {
        Dimension dimension = driver.manage().window().getSize();
        int startX = dimension.width / 2;
        int startY = (int) (dimension.height * 0.8);
        int endY = (int) (dimension.height * 0.2);

        swipe(startX, startY, startX, endY, 1000);
    }

    // Scroll until an element is visible
    public void scrollToElement(MobileElement element) {
        boolean isDisplayed = false;
        while (!isDisplayed) {
            try {
                if (element.isDisplayed()) {
                    isDisplayed = true;
                }
            } catch (Exception e) {
                swipeUp();
            }
        }
    }

    // Drag and drop from one element to another
    public void dragAndDrop(MobileElement source, MobileElement target) {
        new TouchAction<>(driver)
            .longPress(ElementOption.element(source))
            .moveTo(ElementOption.element(target))
            .release()
            .perform();
    }

    // Shake the device (Android only)
    public void shake() {
        driver.executeScript("mobile: shake");
    }

    public static void swipeUntilElementFound(By locator, int maxSwipes) {
        int swipeCount = 0;
        boolean elementFound = false;

        while (swipeCount < maxSwipes) {
            try {
                // Check if the element is present on the screen
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    elementFound = true;
                    System.out.println("Element found after " + swipeCount + " swipes.");
                    break;
                }
            } catch (NoSuchElementException e) {
                // Element not found, continue swiping
                System.out.println("Element not found, swiping...");
            }

            // Swipe left or right (you can alternate or swipe one direction only)
            swipeLeft();  // or swipeRight(); depending on your needs

            // Increment swipe count
            swipeCount++;

            // Optionally, you can add a small wait here to simulate human-like interaction
            try {
                Thread.sleep(1000);  // 1 second wait between swipes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!elementFound) {
            System.out.println("Element not found after " + maxSwipes + " swipes.");
        }
    }
	
    public static void swipeLeft() {
        // Get screen dimensions
        Dimension dimension = driver.manage().window().getSize();
        
        // Calculate swipe start and end points
        int startX = (int) (dimension.width * 0.8);  // Start on the right side
        int startY = dimension.height / 2;           // Middle vertically
        int endX = (int) (dimension.width * 0.2);    // End on the left side
        int endY = dimension.height / 2;             // Middle vertically

        // Perform the swipe action (right to left)
        new TouchAction<>(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))  // Optional wait time
            .moveTo(PointOption.point(endX, endY))
            .release()
            .perform();
    }

}
