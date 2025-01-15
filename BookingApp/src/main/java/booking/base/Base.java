package booking.base;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.naming.Context;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.WithAttachments;

public class Base {

	protected static AndroidDriver <MobileElement>driver;
	protected FileInputStream inputStream;
	protected static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static String file = "C:\\Users\\Yasmi\\eclipse 9-2022\\BookingApp\\Snapshots";
	private Process recordingProcess;
    private static final String VIDEO_FILE = "output.mp4";
    public static String bookingAppPackage="com.booking";
    public static String bookingAppActivity="com.booking.startup.HomeActivity";
    public static String gmailAppPackage="com.google.android.gm";
    public static String gmailAppActivuty="com.google.android.gm.ConversationListActivityGmail";

	
	@BeforeSuite
	public  void beforeSuite() {
		extent = new ExtentReports("Reports/index.html");
		extent.addSystemInfo("Platform Type", "Android");
		extent.addSystemInfo("Android Version", "14");
		extent.addSystemInfo("Device type", "Pixcel Fold");
		extent.addSystemInfo("App Name", "Booking");
	}
	
  //@Parameters({"deviceName","platformName"})
	
	public AndroidDriver <MobileElement> setUp(){	
		
		File propFile= new File("src/main/resources/config/config.properties");
		 try {
			inputStream= new FileInputStream (propFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    prop= new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if(platformName.equalsIgnoreCase("Android")) {
		File app= new File(prop.getProperty("androidAppPath"));
		DesiredCapabilities caps= new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "PixelPro");
		//caps.setCapability(MobileCapabilityType.DEVICE_NAME, "VanillaIceCreamPro7");
		//caps.setCapability(MobileCapabilityType.DEVICE_NAME, "TiramisuUpdate");
		//caps.setCapability(MobileCapabilityType.DEVICE_NAME, "VanillaIceCream");
		//caps.setCapability(MobileCapabilityType.DEVICE_NAME, "TiramisuPrivacySandbox");
		//caps.setCapability(MobileCapabilityType.DEVICE_NAME, "PixelPhone");
		caps.setCapability("recordVideo", true);
		caps.setCapability("videoSavePath", "C:\\Users\\Yasmi\\git\\Mobile_FrameWork\\BookingApp\\Snapshots");		
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,prop.getProperty("androidAutomationName"));
		caps.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
		caps.setCapability("newCommandTimeout", 120000);
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,bookingAppPackage);
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,bookingAppActivity);
	
	
		try {
			driver = new AndroidDriver <MobileElement>(new URL(prop.getProperty("appuimServerLink")), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(300,TimeUnit.SECONDS);
		return driver;
		/*}else if(platformName.equalsIgnoreCase("ios")) {
			File app= new File(prop.getProperty("iosAppPath"));
			DesiredCapabilities caps= new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,prop.getProperty("iosAutomationName"));
			caps.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
			caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.booking");
			caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.booking.startup.HomeActivity");
			driver = new AndroidDriver <MobileElement>(new URL(prop.getProperty("appuimServerLink")), caps);	
		}*/
		
	}
	
	public void launchGmailApp() {
	     Activity activity = new Activity(gmailAppPackage,gmailAppActivuty);
	     driver.startActivity(activity);
	}
	

	
	public void screenshot(String fileName) throws IOException{
	    File srcFile=driver.getScreenshotAs(OutputType.FILE);
	    File targetFile=new File(System.getProperty("user.dir")+ File.separator +"Snapshots/"+fileName +".jpg");
	    FileUtils.copyFile(srcFile,targetFile);
	}
	
	

	
	@Attachment(value = "Test Execution Video", type = "video/mp4")
    public static byte[] attachVideo() throws IOException {
        File videoFile = new File("output.mp4");
        byte[] bytes = new byte[(int) videoFile.length()];
        try (FileInputStream fis = new FileInputStream(videoFile)) {
            fis.read(bytes);
        }
        return bytes;
    }

	

	
	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
}
