package booking.screens;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;
import booking.utils.WaitUtils;


public class NotificationScreen extends Base {
	
	public NotificationScreen() {
	 	   PageFactory.initElements(driver, this);

	}	
	
	 @FindBy(xpath="(//android.widget.Button)[1]")
	    private WebElement notNowBtn;
	  
   public void notAllowNotifications() throws InterruptedException {
	      WaitUtils.waitUntilElementVisible(notNowBtn);
		   notNowBtn.click();
		
   }
}
