package booking.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class SavedScreen  extends Base{
	StaysScreen staysScreenObj;
	 
	public SavedScreen(AndroidDriver <MobileElement> driver) {
		// this.driver = driver;
  	   PageFactory.initElements(driver, this);
  	 staysScreenObj=new StaysScreen(driver);

	}	
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,\"property\")]")
	private WebElement savedItem;
	
	@FindBy(xpath="(//android.widget.ImageView[@resource-id=\"com.booking:id/navigation_bar_item_icon_view\"])[2]")
    private WebElement savedIcon;
	
	  @Step("Check item saved to the favourite list")
	public boolean checkSavedItem() {
		staysScreenObj.backFromSearchResult();
		
		savedIcon.click();
		
		if(savedItem.isDisplayed()) {
			return true;
		}
		return false;
	}
}
