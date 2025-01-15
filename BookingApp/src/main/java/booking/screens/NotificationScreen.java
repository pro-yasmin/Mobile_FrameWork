package booking.screens;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;
import booking.utils.WaitUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class NotificationScreen{
	AndroidDriver <MobileElement> driver;
	
	public NotificationScreen(AndroidDriver <MobileElement> driver) {
	 	   PageFactory.initElements(driver, this);
	 	   this.driver=driver;

	}	
	
	 @FindBy(xpath="(//android.widget.Button)[1]")
	    private WebElement notNowBtn;
	  
   public void notAllowNotifications() throws InterruptedException {
	      WaitUtils.waitUntilElementVisible(notNowBtn,driver);
		   notNowBtn.click();
		
   }
}
