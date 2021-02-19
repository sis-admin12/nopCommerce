package com.nopcommerce.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nopcommerce.utilities.ReadConfig;


public class BaseClass {
	
	ReadConfig readConfig= new ReadConfig();

	public String baseURL=readConfig.getApplicationURL();
	public String username=readConfig.getUseremail();
	public String password=readConfig.getPassword();
	public static WebDriver driver;
	
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger = Logger.getLogger("eCommerce"); //Added logger
		PropertyConfigurator.configure("Log4j.properties");//Added logger
		
		if (br.equals("firefox")) {
			// Firefox Browser
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			// opens the browser
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		}
		
		else if (br.equals("ie")) {
			// opens the browser
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}

		// Global implicit Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		logger.info("close browser");
	}
	

	
	
	
}
