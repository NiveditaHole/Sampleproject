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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Library.UtilitySS;

public class ExtendReportDemo {

    
	
    ExtentReports reports;
    WebDriver driver;
    ExtentTest logger;
 

	@BeforeSuite
    public void setup() {
		// extent report
		reports = new ExtentReports("./Reports/Test.html");
		//htmlreporter = new ExtentHtmlReporter(new File(("./Reports/test" +UtilitySS.getCurrentDateTime()   + ".html")));
		
	}


  
	@AfterMethod
	public void getResult(ITestResult result) throws IOException

	{
		if (result.getStatus() == ITestResult.FAILURE) {
            
			/*
			test.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilitySS.CaptureScreenshots(driver)).build());
			test.fail(result.getThrowable());
			*/
			String screenshotpath=UtilitySS.CaptureScreenshots(driver);
			String image= logger.addScreenCapture(screenshotpath);
			
			logger.log(LogStatus.FAIL, "Test cases fail", image);

			driver.close();

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			String screenshotpath=UtilitySS.CaptureScreenshots(driver);
			logger.log(LogStatus.PASS, "Test cases Pass", screenshotpath);

			driver.close();

		}
		else if (result.getStatus() == ITestResult.SKIP) {
 
			
			//test.skip(result.getThrowable());

		}
	}
   

	@AfterSuite
	public void tearDown()
	{
		
		reports.flush();
	
		//driver.quit();

	}
	
	
	
	
}
