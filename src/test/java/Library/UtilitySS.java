package Library;

import java.io.File;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

public class UtilitySS {
	
	//WILL PASS DRIVER AS PARAMETER
	public static String CaptureScreenshots(WebDriver driver)
	{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//coppying file from src to dest will use FileUtils class with CopyFile method with two arg src anad dest
		String screenshotpath= System.getProperty("user.dir") + "/Screenshots/test" + getCurrentDateTime()   + ".png";
		
		try {
			
			FileHandler.copy(src, new File(screenshotpath));
		    System.out.println("screenshot captured");
		
			//scr and type of ss   
			
	    	}
		catch (Exception e) {
			System.out.println("Exception while taking ss" +e.getMessage());
		
		}
		return screenshotpath;
		
    
		
	}
	public static String getCurrentDateTime()
	{
	DateFormat customformat= new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
	Date currentdate= new Date();
	return customformat.format(currentdate);
	}

	
	

}
