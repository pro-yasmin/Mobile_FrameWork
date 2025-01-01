package booking.screens;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booking.base.Base;
import booking.utils.ActionsUtils;
import booking.utils.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Allure;


public class StaysScreen extends Base {
	
	
	public StaysScreen() {
		 this.driver = driver;
  	   PageFactory.initElements(driver, this);
	}	
	
	 @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Profile\"]")
     private WebElement profile;
     
	 @FindBy(xpath = "//android.view.View[@resource-id=\"sr_list\"]/android.view.View[3]")
     private WebElement lowestHotel;

	 @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.booking:id/select_room_cta\"]")
	// @FindBy(xpath="//android.widget.TextView[@text=\"Chalets at Pyramids Porto Sokhna by Easy Rent 2\"]")
	// @FindBy(xpath="//android.view.View[@resource-id=\"sr_list\"]/android.view.View[3]")
     private WebElement selectRoomBtn;
	 
	 //@FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"com.booking:id/room_selection_button\"]/android.view.View/android.view.View/android.widget.Button")
	 @FindBy(xpath="//android.view.View[@resource-id=\"android:id/navigationBarBackground\"]")
     private WebElement selectBtn;
	 
	 @FindBy(xpath = "//android.widget.TextView[@text=\"Next step\"]")
     private WebElement nextStep;
	 
	 @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.booking:id/room_recycler_view\"]/android.widget.LinearLayout[4]")
     private WebElement stepTwo;
	 
	 @FindBy(xpath = "//android.widget.TextView[@text=\"Confirm\"]")
     private WebElement confirm;
	 
	// @FindBy(id = "com.booking:id/room_pref_select2")
	 @FindBy(xpath="//android.widget.Button[@resource-id=\"com.booking:id/main_action\"]")
     private WebElement reserveBtn;
	 
	 @FindBy(xpath = "//android.widget.Button[@content-desc=\"Enter your destination\"]")
     private WebElement selectDestination;
	 
	 @FindBy(xpath = "//android.widget.EditText")
     private WebElement enterDestination;
	 
	 @FindBy(xpath = "(//android.widget.TextView[@text=\"Ain Sokhna\"])[1]")
     private WebElement resultList;
	 
	 @FindBy(xpath = "(//android.widget.TextView[@text=\"Ain Sokhna\"])[1]")
     private WebElement selectFirstOption;
	 
	 @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.booking:id/bui_tab_container_tab_item_text\" and @text=\"I'm flexible\"]")
     private WebElement selectFlexible;
	 
	 @FindBy(xpath = "//android.widget.Button")
     private WebElement selectDatesBtn;
	 
	 @FindBy (xpath="(//android.widget.TextView[@text=\"2024\"])[3]")
	 private WebElement selectMonth;
	 
	 @FindBy(xpath = "(//android.widget.TextView[@text=\"Search\"])[1]")
     private WebElement searchBtn;
	 
	 @FindBy(xpath = "//android.widget.TextView[@text=\"Filter\"]")
     private WebElement filter;
	 
	 @FindBy(xpath = "//android.widget.TextView[contains(@text,\"Free cancellation \")]")
     private WebElement freeCancellationBox;
	 
	 @FindBy(xpath = "//android.widget.TextView[contains(@text,\"5 stars\")]")
     private WebElement fiveStars;
	 
	 @FindBy(xpath = "//android.widget.TextView[@text=\"Show results\"]")
     private WebElement showResultBtn;
	 
	 @FindBy(xpath = "//android.view.View[@resource-id=\"sr_list\"]/android.view.View")
     private List<WebElement>List ;
	 
	 @FindBy(xpath = "//android.widget.TextView[contains(@text,\"Free cancellation\")]")
     private List<WebElement> filterResultList;
	  
	 @FindBy(xpath = "//android.widget.TextView[@text=\"Sort\"]")
     private WebElement sortResult;
	 
	 @FindBy(xpath = "//android.widget.CheckedTextView[@text=\"Price (low to high)\"]")
     private WebElement sortByPrice;
	 
	 @FindBy(xpath="(//android.widget.ImageView[@content-desc=\"Save property to list\"])[1]")
	 private WebElement favouritIcon;
	 
	 @FindBy(xpath="//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	 private WebElement navigateBack;
	 

	public boolean checkSignInProfile() {
		waitUntilElementVisible(profile);
		 return this.profile.isDisplayed();
	}
	
	
	public boolean reservceATrip() {
	    this.lowestHotel.click();
	   Allure.step("Select the lowset hotel");
	     this.selectRoomBtn.click();
	    
	   // driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Standard Room (Side Sea View) Egyptian and Residence only\").instance(0));").click();
	  //  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"two-Bedroom Apartment\").instance(1));").click();
	  //   this.selectBtn.click();
	  //  this.nextStep.click();
	/*	waitUntilElementVisible(stepTwo);
	     this.stepTwo.click();
	     this.confirm.click();
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Services\").instance(0));");
	     */
	    
	   if (this.reserveBtn.isDisplayed()) {
	    	 Allure.step("Reserve button displayed");
	    	 return true;
	     }
	     Allure.step("Reserve button not displayed");
             return false;	   
	}
	
	public void searchWithDestination(String destination, String tripType) {
		 Allure.step("Search with Destination and dates");
		this.selectDestination.click();
		this.enterDestination.sendKeys(destination);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.selectFirstOption.click();
		this.selectFlexible.click();
		//this.selectMonth.click();
		this.selectDatesBtn.click();
		this.searchBtn.click();
		
		 ActionsUtils.scrollDown();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean filterSearchResult(String filterType){
		  Allure.step("Filter search result with "+ filterType);
		boolean status = true;
		this.filter.click();

	  //   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+filterType+"\").instance(0))").click();
	//	 ActionsUtils.scrollDown();

	/*	if (filterType.contains(Constants.FIVESTARFILTER)) {
			waitUntilElementVisible(fiveStars);

	    	this.fiveStars.click();
		}
		 else if (filterType.contains(Constants.FREECANCELATIONFILTER)){
				waitUntilElementVisible(freeCancellationBox);

				this.freeCancellationBox.click();
		 }*/
		
		this.showResultBtn.click();

		return status;
	}
	
	
	public void sortSearchResult() {
		Allure.step("Sort search result by price");
		this.sortResult.click();
		this.sortByPrice.click();
	}
	
	
	public void addToFavourite() {
		  Allure.step("Add hotel to favourite");
		this.favouritIcon.click();
	}
	
	public void backFromSearchResult() {
		 Allure.step("Back from search result");
		this.navigateBack.click();
	}
	
	public static MobileElement scrollHorizontallyUntilElementFound(String elementText) {
        int maxScrolls = 10; // Set a limit to prevent infinite scrolling
        int currentScrolls = 0;

        // Get the screen size of the device
        Dimension screenSize = driver.manage().window().getSize();
        int startX = (int) (screenSize.width * 0.8); // Start 80% from the left
        int endX = (int) (screenSize.width * 0.2);   // End 20% from the left
        int y = (int) (screenSize.height * 0.5);    // Vertically center of the screen

        while (currentScrolls < maxScrolls) {
            try {
                // Try to find the element by its text
                MobileElement element = (MobileElement) driver.findElementByXPath(
                    "//android.widget.TextView[@text='" + elementText + "']"
                );

                // If found, return the element
                return element;
            } catch (Exception e) {
                // If not found, perform horizontal swipe
                new TouchAction<>(driver)
                    .press(PointOption.point(startX, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endX, y))
                    .release()
                    .perform();

                currentScrolls++;
            }
        }

        // If the element is not found after scrolling
        throw new RuntimeException("Element with text '" + elementText + "' not found after " + maxScrolls + " scrolls.");
    }
	
	
}
