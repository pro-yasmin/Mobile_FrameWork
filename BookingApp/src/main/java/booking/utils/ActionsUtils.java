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
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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
 

    public static void swipeLeftAndRightUntilElementFound(By locator, int maxSwipes) {
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
	
 /*   public static void swipeLeft() {
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
    }*/
    
    
    public static void scrollDown() {
        //if pressX was zero it didn't work for me
        int pressX = driver.manage().window().getSize().width / 2;
        // 4/5 of the screen as the bottom finger-press point
        int bottomY = driver.manage().window().getSize().height * 4/5;
        // just non zero point, as it didn't scroll to zero normally
        int topY = driver.manage().window().getSize().height / 8;
        //scroll with TouchAction by itself
        scroll(pressX, bottomY, pressX, topY);
    }

    
    public static void scroll(int fromX, int fromY, int toX, int toY) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(fromX, fromY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(toX, toY)).release().perform();
    }
    
    public static void swipeUp()
    {
    	 int startY = driver.manage().window().getSize().height / 1;
         int  startX =(int) ( driver.manage().window().getSize().width *0.90);
         int endX = (int)(driver.manage().window().getSize().width *0.05);
         
         scroll(startX, endX,endX, startY);   
    }
    
    public static void swipeLeft()
    {
    	 int startY = driver.manage().window().getSize().height * 2;
         int  startX =(int) ( driver.manage().window().getSize().width *0.99);
         int endX = (int)(driver.manage().window().getSize().width *0.15);
         
         scroll(startX, endX,endX, startY);   
    }
    
   /* public static void swipeleftupdate() {
    	MobileElement emailCell = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text=\"Apr\"]");
    	String text = "Apr";
    	UiSelector element = new UiSelector().text("Accessibility");
    	element.click()
    }*/
    
    public static void verticalScroll()
    {   
        //if pressX was zero it didn't work for me
        int pressX = driver.manage().window().getSize().width *4/5;
        // 4/5 of the screen as the bottom finger-press point
        int bottomY = driver.manage().window().getSize().height/2;
        // just non zero point, as it didn't scroll to zero normally
        int topY = driver.manage().window().getSize().height / 8;
        //scroll with TouchAction by itself
        scroll(pressX, bottomY, pressX, topY);
    }
}
