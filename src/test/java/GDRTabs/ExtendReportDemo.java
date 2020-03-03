 package GDRTabs;

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

    
	
    public  ExtentReports reports;
    public WebDriver driver;
    public ExtentTest logger;
 

	@BeforeSuite
    public void setup() 
	{
		
		System.setProperty("webdriver.chrome.driver", "/home/niveditah/Downloads/chrome80/chromedriver");
	    driver= new ChromeDriver();
	   

	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	    //url 
	    driver.get("https://qa-portal.goodr.co");	
	    
	    driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
		driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_12");
		//login button
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
		System.out.println("login successfully");
		
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
			String screenshotpath= UtilitySS.CaptureScreenshots(driver);
	
		    String image= logger.addScreenCapture(screenshotpath);
			
			logger.log(LogStatus.FAIL, "Test cases fail", image);

			driver.close();
			reports.flush();

		}
 
		else if (result.getStatus() == ITestResult.SUCCESS) {

			String screenshotpath= UtilitySS.CaptureScreenshots(driver);
			 String image= logger.addScreenCapture(screenshotpath);
			logger.log(LogStatus.PASS, "Test cases Pass", image);

			driver.close();
			reports.flush();

		}
		else if (result.getStatus() == ITestResult.SKIP) {
 
			logger.log(LogStatus.SKIP, "Test case skipped");
			reports.flush();

		}
	}
   

	@AfterSuite
	public void tearDown()
	{
		reports.endTest(logger);
		
	
		
	    // to display the url of the report
		//driver.get("./Reports/Test.html");

	}
	
	
	
	
}
