package GDRTabs;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import Library.isEmailValid;

public class LoginFunctionality extends ExtendReportDemo   {

	
	
	@BeforeMethod
   public void Login() 
	{
		
	System.setProperty("webdriver.chrome.driver", "/home/niveditah/Downloads/chrome80/chromedriver");
	ChromeOptions option=new ChromeOptions();
	
    driver= new ChromeDriver(option);
    
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
    //url 
    driver.get("https://qa-portal.goodr.co");	
	
    
	}
	@Test
    public void LoginBlankdata() throws InterruptedException
    {
		
		
        logger=reports.startTest("LoginBlankdata");
        logger.log(LogStatus.INFO, "Test cases started");   
	   //login button
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
		
		
		//to find the text is present when login button entered
		WebElement validationALert = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"));
		String string = validationALert.getText();
		System.out.println(string);
		//Assert.assertEquals("Google Search", string);
	 /*     //My Account will be clicked only if the above condition is true
	 if(driver.getPageSource().contains("Please enter fields"))
	 {
	System.out.println("validation is present for login button with empty username and password");	 
	 }
	 */
    
	
    }
	
	@Test
	public void CheckInvalidEmailId() throws InterruptedException
	{

		 logger=reports.startTest("CheckInvalidEmailId");
		  logger.log(LogStatus.INFO, "Test cases started for checking Invalid email id");  
    	driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("gfhfghgmail.com");
    	
    	//changing path for test fail- adding 1 to id
	    WebElement targetEmail = driver.findElement(By.xpath("//*[@id=\"emailadd\"]"));
	   
	   
	    String getValue = targetEmail.getAttribute("value");
		System.out.println("email entered is :" +getValue);
		
		if (isEmailValid.isValid(getValue))
		{
		 
	        
	            System.out.print("email is Valid"); 
	 	}
	        else
	        {       System.out.print("email is not valid"); 
		
	        }

		
   	}
   	
	@Test
	public void CheckValidEmailId() throws InterruptedException
	{
       
		logger=reports.startTest("CheckValidEmailId");
		logger.log(LogStatus.INFO, "Test cases started for checking valid email id");  
		//driver.findElement(By.xpath("//*[@id=\"emaadd\"]")).sendKeys("abc@gmail.com");
    	driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("abc@gmail.com");
	    WebElement targetEmail = driver.findElement(By.xpath("//*MavenGDRproject/[@id=\"emailadd\"]"));
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
	
	@Test
	public void LoginBlankUsername()
    {
		
	 logger=reports.startTest("LoginBlankUsername");	
	 logger.log(LogStatus.INFO, "Test cases started for blank user name validation");  
	 //login button
	 driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_123");
	 driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
	 
	 if(driver.getPageSource().contains("Please enter fields"))
	 {
	System.out.println("validation is present for Empty userNAME");	 
	 }
	 
	 
    }
	
	@Test
	public void LoginwithValidData()
	{
		
		 logger=reports.startTest("LoginwithValidData");	
		 logger.log(LogStatus.INFO, "Test cases started for valid data login");  
		   driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
			driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_12");
			
			//login button
			driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
            System.out.println(driver.getTitle()); 
		
			
	
	}
}
	
	
	
	 
	
	

