package booking.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class ActionsUtils {
	
    public ActionsUtils() {
    }

    // Tap on a mobile element
    public void tap(MobileElement element,AndroidDriver <MobileElement> driver) {
        new TouchAction<>(driver)
            .tap(ElementOption.element(element))
            .perform(); 
    }
 

   public static void scrollUntilElementFound(WebElement locator,AndroidDriver <MobileElement> driver) {
	   
   }
   
   public static void scrollUntilTextDisplayed(String text,AndroidDriver <MobileElement> driver) {
	   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Standard Room (Side Sea View) Egyptian and Residence only\").instance(0));").click();
   }
    
    public static void scrollDown(AndroidDriver <MobileElement> driver) {
        //if pressX was zero it didn't work for me
        int pressX = driver.manage().window().getSize().width / 2;
        // 4/5 of the screen as the bottom finger-press point
        int bottomY = driver.manage().window().getSize().height * 4/5;
        // just non zero point, as it didn't scroll to zero normally
        int topY = driver.manage().window().getSize().height / 8;
        //scroll with TouchAction by itself
        scroll(pressX, bottomY, pressX, topY,driver);
    }

    
    public static void scroll(int fromX, int fromY, int toX, int toY,AndroidDriver <MobileElement> driver) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(fromX, fromY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(toX, toY)).release().perform();
    }
    
    public static void swipeUp(AndroidDriver <MobileElement> driver)
    {
    	 int startY = driver.manage().window().getSize().height / 1;
         int  startX =(int) ( driver.manage().window().getSize().width *0.90);
         int endX = (int)(driver.manage().window().getSize().width *0.05);
         
         scroll(startX, endX,endX, startY,driver);   
    }
    
    public static void swipeLeft(AndroidDriver <MobileElement> driver)
    {
    	 int startY = driver.manage().window().getSize().height * 2;
         int  startX =(int) ( driver.manage().window().getSize().width *0.99);
         int endX = (int)(driver.manage().window().getSize().width *0.15);
         
         scroll(startX, endX,endX, startY,driver);   
    }
    
 
    
    public static void verticalScroll(AndroidDriver <MobileElement> driver)
    {   
        //if pressX was zero it didn't work for me
        int pressX = driver.manage().window().getSize().width *4/5;
        // 4/5 of the screen as the bottom finger-press point
        int bottomY = driver.manage().window().getSize().height/2;
        // just non zero point, as it didn't scroll to zero normally
        int topY = driver.manage().window().getSize().height / 8;
        //scroll with TouchAction by itself
        scroll(pressX, bottomY, pressX, topY,driver);
    }
    
    public static String split(String text){
	      String code[] = (text).split("[-\\s]");
			 String verificationCode=code[2];
		       return verificationCode;
	}
}
