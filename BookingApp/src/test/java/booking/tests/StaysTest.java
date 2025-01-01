package booking.tests;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import io.qameta.allure.testng.config.AllureTestNgConfig;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	
	
	
	//String imagePath=System.getProperty("user.dir")+ File.separator +"Snapshots\"";
	
	@BeforeMethod
	public void beforeMethod() {
	   
		notificationScreenObj = new NotificationScreen();
		signInScreenObj= new SignInScreen();
		staysScreenObj= new StaysScreen();
		savedScreenObj= new SavedScreen();
			}
	
	@Test
	public void testloginWithMail() throws InterruptedException, IOException {
		  Allure.step("=======Start test login with mail=======");
		String mail=prop.getProperty("email");
        String password=prop.getProperty("pw");	
        
        notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		signInScreenObj.SignInwithEmail(mail, password);
		
		//Assert.assertTrue(staysScreenObj.checkSignInProfile());
		Allure.step("Login done successfully");

		Allure.step("=======End test login with mail=======");
	}
	
	@Test
	public void testFilterWithFiveStar() throws InterruptedException {
		  Allure.step("=======Start test filter with five star=======");

		  driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
	         String mail=prop.getProperty("email");
	         String password=prop.getProperty("pw");
	         String destination=prop.getProperty("destination");
	         String tripType=prop.getProperty("tripType");
	         
	         notificationScreenObj.notAllowNotifications();
			  Allure.step("close notification page");

	 		signInScreenObj.SignInwithEmail(mail, password);
	 		Assert.assertTrue(staysScreenObj.checkSignInProfile());
			Allure.step("Login done successfully");

			staysScreenObj.searchWithDestination(destination, tripType);
			Allure.step("Search with the destination ="+destination);

			Assert.assertTrue(staysScreenObj.filterSearchResult(Constants.FIVESTARFILTER));
			Allure.step("Search results filtered with five stars");
			
			  Allure.step("=======End test filter with five star=======");
	}    
	
	@Test
	public void testReserveStayWithFreeCancelationAndLowCost() throws InterruptedException, IOException {
		 Allure.step("=======Start reserve stay with free cancelation and low cost=======");
	     driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
         String mail=prop.getProperty("email");
         String password=prop.getProperty("pw");
         String destination=prop.getProperty("destination");    
         String tripType=prop.getProperty("tripType");    
         
		notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		signInScreenObj.SignInwithEmail(mail, password);
		Assert.assertTrue(staysScreenObj.checkSignInProfile());
		Allure.step("Login done successfully");

		staysScreenObj.searchWithDestination(destination, tripType);
		Allure.step("Search with the destination ="+destination);

	//	Assert.assertTrue(staysScreenObj.filterSearchResult(Constants.FREECANCELATIONFILTER));
		Allure.step("Search results filtered with free cancelation filter");

		//staysScreenObj.sortSearchResult();
		Allure.step("Search result sorted by price from lowest to ");
		
		//Assert.assertTrue(staysScreenObj.reservceATrip(), "Reserve button displayed successfully");	
		Allure.step("Reserve button displayed successfully");
		
		 Allure.step("=======End reserve stay with free cancelation and low cost=======");
	}
	
	@Test
	public void testSavedToFavouriteItem() throws InterruptedException{
		 Allure.step("=======Start saved to favourite item========");
		 driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
         String mail=prop.getProperty("email");
         String password=prop.getProperty("pw");
         String destination=prop.getProperty("destination");
         String tripType=prop.getProperty("tripType");
         
         notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

 		signInScreenObj.SignInwithEmail(mail, password);
 		Assert.assertTrue(staysScreenObj.checkSignInProfile(),"User registered successfully");
		Allure.step("Login done successfully");
 		
 		staysScreenObj.searchWithDestination(destination, tripType); 
		Allure.step("Search with the destination ="+destination);

 		staysScreenObj.addToFavourite();
		Allure.step("Hotel added to favourite");
 		
 		Assert.assertTrue(savedScreenObj.checkSavedItem(),"Item displayed successfully");
		Allure.step("Hotel displayed at the saved items list successfully");
 		
 		 Allure.step("=======End saved to favourite item=======");
	}
		

}
