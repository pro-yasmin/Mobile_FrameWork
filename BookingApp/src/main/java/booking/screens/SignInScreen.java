package booking.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import booking.base.Base;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class SignInScreen extends Base {
	
       public SignInScreen() {
    	   this.driver = driver;
    	   PageFactory.initElements(driver, this);
		}
	
       @FindBy(xpath = "//*[@text=\"Continue with email\"]")
       private WebElement continueWithMailBtn;
       
       @FindBy(xpath="//android.widget.EditText")
       private WebElement emailAddress;
       
       @FindBy(xpath="//android.widget.Button")
       private WebElement continueBtn;
       
       @FindBy(xpath="//android.widget.EditText[@resource-id=\"com.booking:id/identity_text_input_edit_text\"]")
       private WebElement password;
       
       @FindBy(xpath="//android.widget.TextView[@resource-id=\"com.booking:id/identity_landing_social_button_text\" and @text=\"Sign in\"]")
       private WebElement signBtn;
	
	public void SignInwithEmail(String mail, String pw){
		waitUntilElementVisible(continueWithMailBtn);
			this.continueWithMailBtn.click();
			
		waitUntilElementVisible(emailAddress);
		 Allure.step("Add user mail ="+ mail);
			 this.emailAddress.sendKeys(mail);
			 
			 this.continueBtn.click();
			 
		waitUntilElementVisible(password);
		Allure.step("Add password ="+pw);
			 this.password.sendKeys(pw); 
			 this.signBtn.click();
	}
	
}
