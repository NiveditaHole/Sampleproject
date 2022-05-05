

public class ChromeBrowserDemo {
    public static void main(String[] args) {
        
        //Creating a driver object 
        WebDriver driver;
        
        //Setting chrome driver property
        System.setProperty("webdriver.chrome.driver",  "\\chromedriver.exe");
        
        //Instantiating driver object and launching browser
        driver = new ChromeDriver();
      
        driver.get("http://jt-dev.azurewebsites.net/#/SignUp");
        
	Select select= new Select(driver.findElement(By.xpath("//*[@id="language"]/div[1]/span/span[2]")));
			
	List<WebElement> allOptions = select.getOptions();

	System.out.println("The dropdown options are:");
        for(WebElement options: allOptions)
            System.out.println(options.getText());

        
        System.out.println("Select the Option by Index 1");
        select.selectByIndex(1);
        System.out.println("Select value is: " + select.getFirstSelectedOption().getText());
	
	driver.findElementBy(xpath("//input[@id='name']")).sendKeys("Nivedita Datatray Hole");
	driver.findElementBy(xpath("//input[@id='orgName']")).sendKeys("Randstad pvt ltd");
	driver.findElementBy(xpath("//input[@id='singUpEmail']")).sendKeys("niveditahole@gmail.com");
	driver.findElementBy(xpath("//*[@id="content"]/div/div[3]/div/section/div[1]/form/fieldset/div[4]/label")).click();
	driver.findElementBy(xpath("//button[contains(text(), 'Get Started')]")).click();

	String successmsg= driver.findElement(By.xpath("//span[contains(text(), ' A welcome email has been sent. Please check your email. ')]")).getText();

	assertEquals(successmsg, "A welcome email has been sent. Please check your email.");

	
        //Closing the browser
        driver.quit();
 
    }
