package Example;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Library.UtilitySS;
import Library.isEmailValid;

public class Users {

	WebDriver driver;
   ExtentHtmlReporter report;
	ExtentReports extent;
   ExtentTest logger;


	@BeforeSuite
    public void setupSuite() {
		// extent report
		
		//report= new ExtentHtmlReporter(new File("Extent.html"));
		report = new ExtentHtmlReporter(new File(("./Reports/test" +UtilitySS.getCurrentDateTime()   + ".html")));
		extent= new ExtentReports();
		extent.attachReporter(report);
		ExtentTest logger=extent.createTest("LoginTest" ,"Sample desc");
		logger.log(Status.INFO, "Started");
		extent.flush();

    System.setProperty("webdriver.chrome.driver","/home/niveditah/Downloads/chrome74/chromedriver");
    driver= new ChromeDriver();
    WebDriverWait wait= new WebDriverWait(driver, 100);

    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    //url 
    driver.get("http://gdr-qa-dashboard-3-0.eastus.cloudapp.azure.com");
    
    driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
	driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_123");
	//login button
	driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
	//user link
	WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
	UserLink.click(); 
	logger.log(Status.INFO, "Title verified");
	
	}
    @Test 
    (enabled=false)
	public void Emailcheck() throws InterruptedException
	{
    	
    	
    	ExtentTest logger=extent.createTest("eMAILCHECK" ,"DESC");
		logger.log(Status.INFO, "Started");
		extent.flush();
		driver.findElement(By.xpath("//span[@class='invitenewuser']")).click();		
		Thread.sleep(2000);
		driver.findElement(By.id("mat-input-5")).sendKeys("jessica@gmail.com");
	    WebElement targetEmail = driver.findElement(By.id("mat-input-5"));
	    String getValue = targetEmail.getAttribute("value");
		System.out.println("email entered " +getValue);
		
		if (isEmailValid.isValid(getValue))
		{
		 
	        
	            System.out.print("email is Valid"); 
	 	}
	        else
	        {       System.out.print("email is not valid"); 
		
	        }
		
		
    }
	
    /*
	public void Fluentwait()
	{
		
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	    boolean status= driver.findElement(By.xpath("//div[@class='col-5 Food-Delivered']")).isDisplayed();
	    
	    if(status)
	    {
	    	System.out.println("element is present");
	    }
		
		
	}
	*/
	@Test

	public void InviteUser() throws InterruptedException
	{
		
		
		//invite user button
		driver.findElement(By.xpath("//span[@class='invitenewuser']")).click();
		
		
		driver.findElement(By.xpath("//input[@id='mat-input-17']")).sendKeys("Jessica");
		driver.findElement(By.xpath("//input[@id='mat-input-18']")).sendKeys("H");
		driver.findElement(By.xpath("//input[@id='mat-input-19']")).sendKeys("jessica@gmail.com");
		driver.findElement(By.xpath("//input[@id='mat-input-20']")).sendKeys("657657657677");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Select Role= new Select(driver.findElement(By.xpath("//select[@id='mat-input-21']")));
		Role.selectByIndex(0);
		WebElement selectedrole=Role.getFirstSelectedOption();
		String roleselected=selectedrole.getText();
		System.out.println("role selected is " +roleselected);
		driver.findElement(By.xpath("//*[contains(text(), ' Save Updates ')]")).click();
		
		Thread.sleep(1500);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(driver.getPageSource().contains("User already exist"))
		{
          
		System.out.println("Email already exist, change the email id entered");
		driver.findElement(By.id("mat-input-5")).clear();
		driver.findElement(By.id("mat-input-5")).sendKeys("jessicaUser@gmail.com");
		driver.findElement(By.xpath("//*[contains(text(), ' Save Updates ')]")).click();
		}
		else
		{
			System.out.println("Email id entered is unique");	
		}
		
		ExtentTest logger=extent.createTest("Invite user" ,"Sample desc");
		logger.log(Status.INFO, "Started");
		
		extent.flush();
     	}


			
	
	
		
		@Test 
		public void FilterAccount() throws InterruptedException
		{
			
			
		WebDriverWait wait= new WebDriverWait(driver, 100);
		System.out.println("**Checking filters on account ...");	
		//Add filter
		driver.findElement(By.xpath("//span[@class='invitenewuser']//following::button[3]")).click();	
        //select account from dd
		
		Select account= new Select (driver.findElement(By.xpath("//*[@id=\"mat-input-3\"]")));
		//Thread.sleep(3000);
		account.selectByIndex(3);
		WebElement acc=account.getFirstSelectedOption();
		String clientname=acc.getText();
		System.out.println("Client selected is " +clientname);
		WebElement Apply;
		Apply= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Apply')]")));
		Apply.click();
		Thread.sleep(3000);
		UtilitySS.CaptureScreenshots(driver);
		

    	ExtentTest logger=extent.createTest("Filters accound" ,"DESC");
		logger.log(Status.INFO, "Started filter on account test");
		//logger.log(Status., details)
		extent.flush();
    	}
		
		@Test
	
		public void FilterRole() throws InterruptedException
		{
			WebDriverWait wait= new WebDriverWait(driver, 100);
			System.out.println("**Checking filters on Role ...");	
			//Add filter
			driver.findElement(By.xpath("//span[@class='invitenewuser']//following::button[3]")).click();	
	        //select role from dd
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Select role= new Select (driver.findElement(By.xpath("//*[@id=\"mat-input-7\"]")));
			Thread.sleep(2000);
			role.selectByIndex(2);
			WebElement Userrole=role.getFirstSelectedOption();
			String rolename=Userrole.getText();
			System.out.println("Role selected is " +rolename);
			WebElement Apply;
			Apply= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Apply')]")));
			Apply.click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			UtilitySS.CaptureScreenshots(driver);	
			//reset
			driver.findElement(By.xpath("//*[@id=\"Track-Pickup\"]/span")).click();

	    	ExtentTest logger=extent.createTest("Filters accound" ,"DESC");
			logger.log(Status.INFO, "Started filter on account test");
			//logger.log(Status., details)
			extent.flush();
			
		}

		@Test(enabled=false)
		
		public void FilterStatus() throws InterruptedException
		{
			WebDriverWait wait= new WebDriverWait(driver, 100);
			System.out.println("**Checking filters on Role ...");	
			//Add filter
			driver.findElement(By.xpath("//span[@class='invitenewuser']//following::button[3]")).click();	
	        //select role from dd
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Select Userstatus= new Select (driver.findElement(By.xpath("//*[@id=\"mat-input-8\"]")));
			Thread.sleep(2000);
			Userstatus.selectByValue("Active");
			WebElement status=Userstatus.getFirstSelectedOption();
			String status1=status.getText();
			System.out.println("Role selected is " +status1);
			WebElement Apply;
			Apply= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Apply')]")));
			Apply.click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			UtilitySS.CaptureScreenshots(driver);	
			

	    	ExtentTest logger=extent.createTest("Filters accound" ,"DESC");
			logger.log(Status.INFO, "Started filter on account test");
			//logger.log(Status., details)
			extent.flush();
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

