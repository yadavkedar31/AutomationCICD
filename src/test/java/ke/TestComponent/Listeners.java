package ke.TestComponent;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ke.resources.ExtentREporterNG;

public class Listeners extends BaseTest implements ITestListener{
	 ExtentTest test;
	ExtentReports extent = 	ExtentREporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	  @Override
	    public void onTestStart(ITestResult result) {
		 
	        // This method will be invoked before each test method
	      test=  extent.createTest(result.getMethod().getMethodName());
	      extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // This method will be invoked each time a test succeeds
	       test.log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // This method will be invoked each time a test fails
	    	extentTest.get().fail(result.getThrowable());
	    	try {
				driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	
	    	String filepath = null;
			try {
				filepath = getScreenShot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());  }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // This method will be invoked each time a test is skipped
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // This method will be invoked each time a test fails within success percentage
	        System.out.println("Test Failed but within success percentage: " + result.getName());
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // This method will be invoked before the start of all tests
	        System.out.println("Test Started: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // This method will be invoked after all tests have run
	        extent.flush();
	    }
	
	


}
