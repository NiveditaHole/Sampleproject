package GDRTabs;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

import Library.UtilitySS;
import Library.isEmailValid;

public class LoginFunctionality extends ExtendReportDemo {

	@BeforeMethod
	public void Login() {

		System.setProperty("webdriver.chrome.driver", "/home/niveditah/Downloads/chrome80/chromedriver");
		ChromeOptions option = new ChromeOptions();

		driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		// url
		driver.get("https://qa-portal.goodr.co");

	}

	@Test
	public void LoginwithBlankdata() throws InterruptedException {

		logger = reports.startTest("LoginwithBlankdata");
		logger.log(LogStatus.INFO,
				"Checking the click on Login button with the blank values in Username and Password field");
		// login button
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"))
				.click();

		// to find the text is present when login button entered
		WebElement validationALert = driver
				.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"));
		String string = validationALert.getText();
		System.out.println(string);
		// Assert.assertEquals("Google Search", string);
		/*
		 * //My Account will be clicked only if the above condition is true
		 * if(driver.getPageSource().contains("Please enter fields")) { System.out.
		 * println("validation is present for login button with empty username and password"
		 * ); }
		 */

	}

	@Test
	public void CheckInvalidEmailId() throws InterruptedException {

		logger = reports.startTest("CheckingInvalidEmailIdValidation");
		logger.log(LogStatus.INFO, "Executing test case for checking Invalid email id Validation");
		driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("gfhfghgmail.com");
		// LOGIN BUTTON
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"))
				.click();
		// changing path for test fail- adding 1 to id
		WebElement targetEmail = driver.findElement(By.xpath("//*[@id=\"emailadd\"]"));

		String getValue = targetEmail.getAttribute("value");
		System.out.println("email entered is :" + getValue);

		if (isEmailValid.isValid(getValue)) {

			System.out.print("email is Valid");
		} else {
			System.out.print("email is not valid");

		}

	}

	@Test
	public void CheckValidEmailId() throws InterruptedException {

		logger = reports.startTest("CheckValidEmailIdValidation");
		logger.log(LogStatus.INFO, "Executing test case for checking Valid email id Validation");
		driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("abc@gmail.com");
		
		// LOGIN BUTTON
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"))
				.click();
		WebElement targetEmail = driver.findElement(By.xpath("//*[@id=\"emailadd\"]"));
		String getValue = targetEmail.getAttribute("value");
		System.out.println("email entered :" + getValue);

		if (isEmailValid.isValid(getValue)) {

			System.out.print("email is Valid");
		} else {
			System.out.print("email is not valid");

		}

	}

	@Test
	public void LoginBlankUsername() {

		logger = reports.startTest("LoginBlankUsername");
		logger.log(LogStatus.INFO, "Test case for blank username validation");
		// login button
		driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_123");
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"))
				.click();

		if (driver.getPageSource().contains("Please enter fields")) {
			System.out.println("validation is present for Empty userNAME");
		}

	}

	@Test
	public void LoginwithValidData() throws InterruptedException {

		logger = reports.startTest("LoginwithValidData");
		logger.log(LogStatus.INFO, "Test cases started for valid data login");

		driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
		driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_123");

		// login button
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
		// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Thread.sleep(10000);
		System.out.println(driver.getTitle());

	}
	@Test
	public void VerifyTitle() throws InterruptedException  {

		logger = reports.startTest("Verify Title");
		logger.log(LogStatus.INFO, "Test cases started for verifying the title of the portal");

		driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
		driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_123");

		// login button
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button")).click();
		Thread.sleep(10000);
		
		String actualTitle = driver.getTitle();
		
		String expectedTitle = "Dashboard";
		Assert.assertEquals("Condition true", actualTitle, expectedTitle);
	

	        if (actualTitle.equals(expectedTitle)) {
	                   System.out.println("Test Passed!");
	        } else {
	                   System.out.println("Test Failed");
	        }
	}
	
	@Test
	public void LoginInValidCredentials() {

		logger = reports.startTest("LoginwithInvalidCredentials");
		logger.log(LogStatus.INFO, "Test cases started for checking the error message for Invalid credentials Login");
		driver.findElement(By.xpath("//*[@id=\"emailadd\"]")).sendKeys("avinash@trinesis.com");
		driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys("Trinesis_12123123");

		// login button
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-actions/button"))
				.click();

		// verify alert message
		Boolean verifyAlertMessage = driver.getTitle().equalsIgnoreCase("Invalid Username or Password");
		assertFalse(verifyAlertMessage);

	}

	@Test
	public void PrivacyPolicyLink() throws InterruptedException {

		logger = reports.startTest("Verify Privacy Link");
		logger.log(LogStatus.INFO, "Checking privacy policy link is working or Not");
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-content/div/span[1]/a/u/a"))
				.click();
		
	
		Thread.sleep(5000);
		UtilitySS.CaptureScreenshots(driver);
		System.out.println("Privacy link is active ");
		
	}

	@Test
	public void ForgetPasswordLink() {

		logger = reports.startTest("Verify Forget Password Link");
		logger.log(LogStatus.INFO, "Checking Forget password link is working or Not");
		driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div[2]/form/mat-card-content/div/span[2]")).click();
		
		System.out.println("forget password modal opens");

	}
}
