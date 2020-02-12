 package Example;

import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Library.UtilitySS;

public class ExtendReportDemo {

    
	ExtentHtmlReporter htmlreporter;
    ExtentReports reports;
    WebDriver driver;
    ExtentTest test;
 

	@BeforeSuite
    public void setup() {
		// extent report
		htmlreporter = new ExtentHtmlReporter(new File("./Reports/Test.html"));
		//htmlreporter = new ExtentHtmlReporter(new File(("./Reports/test" +UtilitySS.getCurrentDateTime()   + ".html")));
		reports= new ExtentReports();
		reports.attachReporter(htmlreporter);
		
	
	}


  
	@AfterMethod
	public void getResult(ITestResult result) throws IOException

	{
		if (result.getStatus() == ITestResult.FAILURE) {

			test.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilitySS.CaptureScreenshots(driver)).build());
			test.fail(result.getThrowable());

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			test.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilitySS.CaptureScreenshots(driver)).build());

		}
		else if (result.getStatus() == ITestResult.SKIP) {

			test.skip("Test Skipped",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilitySS.CaptureScreenshots(driver)).build());
			//test.skip(result.getThrowable());

		}
	}
   

	@AfterSuite
	public void tearDown()
	{
		
		reports.flush();
		driver.quit();

	}
	
	
	
	
}
