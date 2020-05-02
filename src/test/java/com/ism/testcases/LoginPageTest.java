package com.ism.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ism.base.BaseTest;
import com.ism.pages.LoginPage;

import atu.testrecorder.exceptions.ATUTestRecorderException;



public class LoginPageTest extends BaseTest {
	
	LoginPage loignpage;
	
	public LoginPageTest() {
		//called for superclass constructor - BaseTest() cz properties are defined in there
		super();
	}
	

	@BeforeMethod
	public void setUp() throws ATUTestRecorderException {
		//called for initialization method in BaseTest class
		initialization();
		//create a loginpage object
		loignpage=new LoginPage();
	}
	
	@AfterMethod
	public void tearDown() throws ATUTestRecorderException {
		driver.quit();		
		stoprecording();
	}
	
	/*
	@Test(priority=1,description="Sucessfully ridected to the storeview based on selected country/language")
	@Parameters({"country","language"})
	public void languagePopup(String country,String language) {
				
		loignpage.LanguageSelection(country, language);	
		//Verify navigated to correct store
		String storeurlNL=driver.getCurrentUrl();
		Assert.assertTrue(storeurlNL.contains("kinglouie-beta-nl.exotadev.hypernode.io"));
	}
	*/
	
	
	@Test(description="Navigate to NL store via language selector popup")
	public void languagePopup2() {
				
		loignpage.LanguageSelection("Netherlands", "NL");	
		//Verify navigated to correct store
		String storeurlNL=driver.getCurrentUrl();
		Assert.assertTrue(storeurlNL.contains("kinglouie-beta-nl.exotadev.hypernode.io"));
	}
	 
	
	@Test(priority=2,description="Verify sucessfully navigating to the Login page")
	public void sucessfullyLogin()  {
		this.languagePopup2();
		loignpage.navigateToLoginPage();	
		Assert.assertEquals(driver.getTitle(),"Klant Login - King Louie");
		loignpage.login(prop.getProperty("username"), prop.getProperty("username"));
				
	}
	
	//Using TestNG parameters
	@Test
	@Parameters({"username","password"})
	public void sucessfullyLogin2(String username,String pwd)   {
		this.languagePopup2();
		loignpage.navigateToLoginPage();	
		loignpage.login(username,pwd);
				
	}
	
}
