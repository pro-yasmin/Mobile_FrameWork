package booking.tests;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Gmail.Screens.InboxScreen;
import booking.base.Base;
import booking.screens.NotificationScreen;
import booking.screens.SavedScreen;
import booking.screens.SignInScreen;
import booking.screens.StaysScreen;
import booking.utils.Constants;



public class StaysTest extends Base {
	NotificationScreen notificationScreenObj;
	SignInScreen signInScreenObj;
	StaysScreen staysScreenObj;
	SavedScreen savedScreenObj;
	InboxScreen inboxScreenObj;
	protected static AndroidDriver <MobileElement>driver;

	
	
	
	//String imagePath=System.getProperty("user.dir")+ File.separator +"Snapshots\"";
	
	@BeforeMethod
	public void beforeMethod() {
		driver=setUp();
		notificationScreenObj = new NotificationScreen(driver);
		signInScreenObj= new SignInScreen(driver);
		staysScreenObj= new StaysScreen(driver);
		savedScreenObj= new SavedScreen(driver);
		inboxScreenObj= new InboxScreen(driver);
		
		
		
			}
	
	@Test
	public void testloginWithMail() throws InterruptedException, IOException {
		  Allure.step("=======Start test login with mail=======");
		String mail=prop.getProperty("email");
        String password=prop.getProperty("pw");	
        
        notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		 signInScreenObj.signInWithVerificationCode(mail);
		
		launchGmailApp();
		String code= inboxScreenObj.getVerificationCode();		
		driver.activateApp("com.booking");
		
		signInScreenObj.submitVerificationCode(code);

		Assert.assertTrue(staysScreenObj.checkSignInProfile());
		Allure.step("Login done successfully");

		Allure.step("=======End test login with mail=======");
	}
	
	@Test
	public void testFilterWithVeryGood() throws InterruptedException {
		  Allure.step("=======Start test filter with five star=======");

		//  driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
	         String mail=prop.getProperty("email");
	         String password=prop.getProperty("pw");
	         String destination=prop.getProperty("destination");
	         String tripType=prop.getProperty("tripType");
	         
	         notificationScreenObj.notAllowNotifications();
			  Allure.step("close notification page");

			  signInScreenObj.ignoreLogin();
			  Allure.step("Ignore login");
			//signInScreenObj.signInWithVerificationLink(mail);

			//launchGmailApp();
			//inboxScreenObj.VerifySignInMail();
			
			//driver.activateApp("com.booking");
			
	 		//Assert.assertTrue(staysScreenObj.checkSignInProfile());
			//Allure.step("Login done successfully");
		    //	 signInScreenObj.ignoreLogin();

			staysScreenObj.searchWithDestination(destination, tripType);
			Allure.step("Search with the destination ="+ destination);

			Assert.assertTrue(staysScreenObj.filterSearchResult(Constants.VERYGOOD));
			Allure.step("Search results filtered with Very Good");
			
			  Allure.step("=======End test filter with very good=======");
	}    
	
	@Test
	public void testReserveStayWithFreeCancelationAndLowCost() throws InterruptedException, IOException {
		 Allure.step("=======Start reserve stay with free cancelation and low cost=======");
         String mail=prop.getProperty("email");
         String password=prop.getProperty("pw");
         String destination=prop.getProperty("destination");    
         String tripType=prop.getProperty("tripType");    
         
		notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		 signInScreenObj.ignoreLogin();
		  Allure.step("Ignore login");
				
		 //signInScreenObj.signInWithVerificationLink(mail);

		//Assert.assertTrue(staysScreenObj.checkSignInProfile());
		//Allure.step("Login done successfully");

		staysScreenObj.searchWithDestination(destination, tripType);
		Allure.step("Search with the destination ="+destination);

		Assert.assertTrue(staysScreenObj.filterSearchResult(Constants.FREECANCELATIONFILTER));
		Allure.step("Search results filtered with free cancelation filter");

		staysScreenObj.sortSearchResult();
		Allure.step("Search result sorted by price from lowest to ");
		
		Assert.assertTrue(staysScreenObj.reservceATrip(), "Reserve button displayed successfully");	
		Allure.step("Reserve button displayed successfully");
		
		 Allure.step("=======End reserve stay with free cancelation and low cost=======");
	}
	
	@Test
	public void testSavedToFavouriteItem() throws InterruptedException{
		 Allure.step("=======Start saved to favourite item========");
         String mail=prop.getProperty("email");
         String password=prop.getProperty("pw");
         String destination=prop.getProperty("destination");
         String tripType=prop.getProperty("tripType");
         
         notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		// signInScreenObj.signInWithVerificationLink(mail);
		 signInScreenObj.ignoreLogin();
		  Allure.step("Ignore login");
 				
 		staysScreenObj.searchWithDestination(destination, tripType); 
		Allure.step("Search with the destination ="+destination);

 		staysScreenObj.addToFavourite();
		Allure.step("Hotel added to favourite");
 		
 		Assert.assertTrue(savedScreenObj.checkSavedItem(),"Item displayed successfully");
		Allure.step("Hotel displayed at the saved items list successfully");
 		
 		 Allure.step("=======End saved to favourite item=======");
	}
	
	
	@AfterMethod
	public static void afterMethod(){		
	    Allure.addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		driver.closeApp();
	}
	
}
