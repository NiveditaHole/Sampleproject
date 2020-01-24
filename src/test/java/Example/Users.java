package Example;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
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

public class Users extends ExtendReportDemo {


	 
	
	@BeforeMethod
    public void Login() 
	{
		// extent report
		
	test =reports.createTest("LoginTest");
	test.log(Status.INFO, "URL link is open");
	
    
	
    System.setProperty("webdriver.chrome.driver","/home/niveditah/Downloads/chrome74/chromedriver");
    driver= new ChromeDriver();
   

    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    //url 
    driver.get("http://gdr-qa-dashboard-3-0.eastus.cloudapp.azure.com");
    
    driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
	driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_123");
	//login button
	driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
	System.out.println("login successfully");
	}
	
	
	
	 /*@BeforeClass
	 
	 public void UserTab()
	{
		
	WebDriverWait wait= new WebDriverWait(driver, 100);	
	//user link
	WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
	UserLink.click(); 
	System.out.println("User link open successfully");
	
	}
	
	*/
    @Test 
    
	public void Emailcheck() throws InterruptedException
	{
    	
    	test =reports.createTest("Email validation ");
    	test.log(Status.INFO, "Checking the email validation ");
    	WebDriverWait wait= new WebDriverWait(driver, 100);	
    	//user link
    	WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
    	UserLink.click();
		driver.findElement(By.xpath("//span[@class='invitenewuser']")).click();		
		Thread.sleep(2000);
		driver.findElement(By.id("mat-input-5")).sendKeys("gfhfghgmail.com");
	    WebElement targetEmail = driver.findElement(By.id("mat-input-5"));
	    String getValue = targetEmail.getAttribute("value");
		System.out.println("email entered :" +getValue);
		
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
	
    @Test(enabled=false) 
	public void InviteUser() throws InterruptedException
	{
    	
    	WebDriverWait wait= new WebDriverWait(driver, 100);	
		//user link
    	WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
    	UserLink.click(); 

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
		
		
     	}


			
	
	
		
		@Test (enabled=false) 
		public void FilterAccount() throws InterruptedException
		{
			

	    test =reports.createTest("Filtering check on account");
	    test.log(Status.INFO, "Checking the Filter in the User ACoount ");	
	    WebDriverWait wait= new WebDriverWait(driver, 100);	
	  //user link
		WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
		UserLink.click(); 

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
		//UtilitySS.CaptureScreenshots(driver);
		
    	}
		
		@Test
	
		public void FilterRole() throws InterruptedException
		{
			
			test =reports.createTest("Filter modal User");
		    test.log(Status.INFO, "Checking the Filter on the Role field in the User ");
			WebDriverWait wait= new WebDriverWait(driver, 100);
			  //user link
				WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
				UserLink.click(); 
			
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
			//UtilitySS.CaptureScreenshots(driver);	
			//reset
			driver.findElement(By.xpath("//*[@id=\"Track-Pickup\"]/span")).click();

	    	
			
		}

		@Test(enabled=false) 
		public void FilterStatus() throws InterruptedException
		{
			test =reports.createTest("Filtering check");
		    test.log(Status.INFO, "Checking the Filter on the status field in the User ");
			WebDriverWait wait= new WebDriverWait(driver, 100);
			
				
			  //user link
				WebElement UserLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users']")));
				UserLink.click(); 
				//reset
				driver.findElement(By.xpath("//*[@id=\"Track-Pickup\"]/span")).click();
			//Add filter
			driver.findElement(By.xpath("//span[@class='invitenewuser']//following::button[3]")).click();	
	        //select role from dd
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Select Userstatus= new Select (driver.findElement(By.xpath("//*[@id=\"mat-input-8\"]")));
			Thread.sleep(2000);
			Userstatus.selectByValue("Active");
			WebElement status=Userstatus.getFirstSelectedOption();
			System.out.println("Role selected is " +status);
			WebElement Apply;
			Apply= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Apply')]")));
			Apply.click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			//UtilitySS.CaptureScreenshots(driver);	
			

	    	
		}
		
	
}

