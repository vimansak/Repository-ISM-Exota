package com.ism.pages;
import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ism.base.BaseTest;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class LoginPage extends BaseTest{
	
	//testgfdgfd
	

	/* @FindBy(how=How.ID,using="userName")
	 private WebElement txtbx_UserName; */
	 
	@FindBy(name="login[username]")
	WebElement txtUsername;
	
	//@FindBy(name="login[password]")
	@FindBy(css="input[type='password']")
	WebElement txtPassword;
	//input[type='password']
	
	@FindBy(css="button[class=\"action login primary\"]")
	WebElement btnSubmitLogin;
	
	@FindBy (css=".authorization-link a")
	WebElement linkLogin;
	
	@FindBy (css=".header-right-col.toggle-search button[data-action='customer-menu-toggle']")
	WebElement btnAccount;
		
	@FindBy(css=".language-selector")
	WebElement popLanguage;
	
	@FindBy(css=".action-close")
	WebElement btnlanguageClose;
	
	@FindBy(id ="selectCountry")
	WebElement drpCountry;
	
	@FindBy(id ="selectLanguage")
	WebElement drplanguage;
	
	@FindBy(css=".modal-inner-wrap .modal-footer button")
	WebElement btnGo;
	
	
	//constructor/Initializing the Page Objects
	public LoginPage()
	{
		///This method will create all WebElements
		PageFactory.initElements(driver, this);		
	}
	
	//Navigate to Login page via top header account
	public void navigateToLoginPage() {
		
		ClickifAvailable(btnAccount);
		linkLogin.click();
	}
	
			
	public void login(String un,String pwd)    {
					  	   
	    txtUsername.click();
		txtUsername.sendKeys(un);
		
		FluentWait<WebDriver> wait2 = new FluentWait<WebDriver>(driver);	
		wait2.withTimeout(Duration.ofSeconds(2));
		
		if (waitForElementToClickable(txtPassword,6000)== true) {
			txtPassword.click();
			txtPassword.sendKeys(pwd);
			}
		
		
	
		if (waitForElementToClickable(btnSubmitLogin,60)== true) {
		btnSubmitLogin.click();
		}
	    
	} 
	

public void closeLanguagePopup() {

	
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);						
	
	wait.pollingEvery(Duration.ofMillis(250));
	wait.withTimeout(Duration.ofSeconds(2));	
	wait.ignoring(NoSuchElementException.class);
	 WebElement langugepoup = wait.until(new Function<WebDriver, WebElement>(){
	
		public WebElement apply(WebDriver driver ) {
			return driver.findElement(By.cssSelector(".language-selector"));
		}
	});
	 
	 //click on close button
	 if (waitForElementToClickable(btnlanguageClose,600)== true) {
			btnlanguageClose.click();
			}
	}

public void LanguageSelection(String country,String language)  {
	
//wait for language pop up to be displayed
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);						
	
	wait.pollingEvery(Duration.ofMillis(250));
	wait.withTimeout(Duration.ofSeconds(2));	
	wait.ignoring(NoSuchElementException.class);
	 WebElement langugepoup = wait.until(new Function<WebDriver, WebElement>(){
	
		public WebElement apply(WebDriver driver ) {
			return driver.findElement(By.id("languageSelector"));
		}
	});
	 

	//Select countryDropdown= new Select(drpCountry);
	boolean waitforDrp= waitForElementToClickable(drpCountry,2000);
	if (waitforDrp=true) {
    Select countryDropdown= new Select(driver.findElement(By.id("selectCountry")));
	countryDropdown.selectByVisibleText(country);
	}
	
		
	Select languageDropdown= new Select(drplanguage);
	languageDropdown.selectByVisibleText(language);
	
//Click on go button
	ClickifAvailable(btnGo);
	
}

public void stoprecordings() throws ATUTestRecorderException {
	stoprecording();
}


	
}

