package booking.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import booking.base.Base;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
;

public class NotificationScreen extends Base {
	
	public NotificationScreen() {
		
	}	
	
	
	  
   public void notAllowNotifications() throws InterruptedException {
	   MobileElement notNowBtn= driver.findElement(By.xpath("(//android.widget.Button)[1]"));
		 WebDriverWait wait = new WebDriverWait(driver, 70);
		   wait.until(ExpectedConditions.visibilityOf(notNowBtn));
	  
		   notNowBtn.click();
		
   }
}
