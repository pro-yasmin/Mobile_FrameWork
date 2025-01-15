package Gmail.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;
import booking.utils.ActionsUtils;
import booking.utils.WaitUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class InboxScreen extends Base {
	AndroidDriver <MobileElement> driver;
	
    public InboxScreen(AndroidDriver <MobileElement> driver) {
 	   PageFactory.initElements(driver, this);
 	   this.driver=driver;
		}
    
    @FindBy(id="com.android.permissioncontroller:id/permission_deny_button")
    private WebElement denyNotification;
    
    @FindBy(xpath = "(//android.widget.TextView[contains(@text,\"Booking.com\")])[1]")
    private WebElement bookingVerificationMail;
    
    @FindBy(xpath="//android.widget.TextView[@resource-id=\"com.google.android.gm:id/senders\" and contains( @text,\"Booking.com\"])")
    private WebElement verifyBtn;
    
    @FindBy(xpath="//android.widget.TextView[@text=\"Verify me\"]")
    private WebElement verifyBtnLastMail;
    
    @FindBy(xpath="//android.widget.RelativeLayout[@resource-id=\"com.google.android.gm:id/title_container\"]")
    private List<WebElement> mailsList;
    
    @FindBy(xpath="//android.widget.TextView[@text=\"Show quoted text\"]")
    private WebElement showLastMailDetails;
    
    @FindBy(xpath="(//android.widget.TextView[@resource-id=\"com.google.android.gm:id/subject\"])[1]")
	 private WebElement mailTitle;
    
    
    public void VerifySignInMail(){
    	// WaitUtils.waitUntilElementVisible(denyNotification);
    	//denyNotification.click();
    	
		   WaitUtils.waitUntilElementVisible(bookingVerificationMail,driver);
			bookingVerificationMail.click();
			
			WaitUtils.waitUntilElementVisible(showLastMailDetails,driver);
			if(showLastMailDetails.isDisplayed()) {
				showLastMailDetails.click();	
				ActionsUtils.scrollDown(driver);
				verifyBtnLastMail.click();
				
			}else {
			verifyBtn.click();
			}
    }
    
    private void selectTheLatestMail() {
    	WebElement latestMail = mailsList.get(mailsList.size() -1);
    	
    	WaitUtils.waitUntilElementVisible(latestMail,driver);
    	latestMail.click();

    }
    
    public  String getVerificationCode(){
    try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		   WaitUtils.waitUntilElementVisible(bookingVerificationMail,driver);

    	String mail=mailTitle.getAttribute("text");
	      String code[] = (mail).split("[-\\s]");
			 String verificationCode=code[2];
		
			System.out.println(verificationCode);
		return verificationCode;
	}
}
    
    
