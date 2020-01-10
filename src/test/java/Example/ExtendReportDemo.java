package Example;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

	ExtentHtmlReporter report;
	ExtentReports extent;
	
	
	  
	
   WebDriver driver;
   ExtentTest logger;


	@BeforeMethod
    public void setupSuite() {
		// extent report
		
		//report= new ExtentHtmlReporter(new File("Extent.html"));
		report = new ExtentHtmlReporter(new File(("./Reports/test" +UtilitySS.getCurrentDateTime()   + ".html")));
		extent= new ExtentReports();
		extent.attachReporter(report);
		ExtentTest logger=extent.createTest("LoginTest" ,"Sample desc");
		logger.log(Status.INFO, "Started");
		extent.flush();
	}
	@Test
	public void loginTest() throws IOException
      
	{  
		System.setProperty("webdriver.chrome.driver", "/home/niveditah/Downloads/chrome74/chromedriver");
		driver=new ChromeDriver();
		
		driver.get("http://gdr-qa-dashboard-3-0.eastus.cloudapp.azure.com");
		System.out.println(driver.getTitle());
		logger.log(Status.INFO, "Title verified");
		 
	}
	
	

  
	@AfterSuite
	public void TearDown(ITestResult result) throws IOException

	{
		if (result.getStatus() == ITestResult.FAILURE) {

			logger.fail("Test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilitySS.CaptureScreenshots(driver)).build());

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.fail("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilitySS.CaptureScreenshots(driver)).build());

		}
		report.flush();

	}
	
	
	
}
