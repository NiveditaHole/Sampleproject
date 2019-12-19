package Example;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Demo {

	@Test
	public void OpenBrowser() {

		System.setProperty("webdriver.chrome.driver", "/home/niveditah/Downloads/chrome74/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

		driver.get("http://gdr-qa-dashboard.eastus.cloudapp.azure.com");
		System.out.println(driver.getTitle());
	}

}
