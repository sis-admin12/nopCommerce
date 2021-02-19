package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;


public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException {
	driver.get(baseURL);
	driver.manage().window().maximize(); 
		
	
		logger.info("url is opened...");
		LoginPage lp = new LoginPage(driver);
		
		
		lp.setUserName(username);
		logger.info("user nane is set");
		
		
		lp.setPassword(password);
		logger.info("password is set");
		
		
		lp.clickLogin();
		logger.info("clik login btn");

		Thread.sleep(2000);
		
		if (driver.getTitle().equals("Gresit Dashboard / nopCommerce administration")) {
			logger.info("verification title of web page");
			Thread.sleep(5000);
			lp.clickLogout();
			Assert.assertTrue(true);
			logger.info("succes, logout");
		} else {
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("failed");
			
			
		}

	}

}
