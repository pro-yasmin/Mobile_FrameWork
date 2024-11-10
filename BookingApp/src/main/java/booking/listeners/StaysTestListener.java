package booking.listeners;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class StaysTestListener implements ITestListener{

	
	public void onTestFailure(ITestResult result) {
		if(result.getThrowable() !=null) {
			StringWriter sw= new StringWriter();
			PrintWriter pw= new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}
		
	}
}
