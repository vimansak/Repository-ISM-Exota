package com.ism.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ism.base.BaseTest;
import com.ism.pages.LoginPage;
import com.ism.pages.RegisterPage;
import com.ism.qa.util.TestUtil;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class RegisterPageTest extends BaseTest{
	RegisterPage registerpage;
	LoginPage loginpage;
	
	String sheetName="register";

	
	public RegisterPageTest() {
		//called for superclass constructor - BaseTest() cz properties are defined in there
				super();
	}
	@BeforeMethod
	public void setUp() throws ATUTestRecorderException {
		//called for initialization method in BaseTest class
		initialization();
		registerpage =new RegisterPage();
		loginpage =new LoginPage();
		

		
	}
	
	@AfterMethod
	public void tearDown() throws ATUTestRecorderException {
		driver.quit();		
		stoprecording();
	}
	
	//return all the data in excel sheet -register
	@DataProvider
	public  Object[][]  getRegisterTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;			
	}
	
	@Test(description="Create new user account",dataProvider="getRegisterTestData")
	public void createNewAccount() {
		
		//Go to NL store
		loginpage.LanguageSelection("Netherlands", "NL");	
		String storeurlNL=driver.getCurrentUrl();
		registerpage.gotoAccountCreationPage();				
	}
	

}
