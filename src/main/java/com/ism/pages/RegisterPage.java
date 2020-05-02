package com.ism.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ism.base.BaseTest;

public class RegisterPage extends BaseTest{

	//weElements
	@FindBy (css=".header-right-col.toggle-search button[data-action='customer-menu-toggle']")
	WebElement btnAccount2;
	
	@FindBy (css=".authorization-link a[text()='Inloggen']")
	WebElement	menuItemLogin;
	
	@FindBy (css=".authorization-link a")
	WebElement linkLogin2;
	
	
	//@FindBy(css=".block-new-customer a[contains(@href,'create')]")
	@FindBy (css=".block-new-customer a[text()='Account aanmaken']")
	WebElement btnCreateAccount;
		
	
	//constructor/Initializing the Page Objects
	public  RegisterPage() {
		///This method will create all WebElements
				PageFactory.initElements(driver, this);	
	}
	
	public void gotoAccountCreationPage() {
		//topbat account link
		ClickifAvailable(btnAccount2);
		linkLogin2.click();				
	   		//account creation btn
		ClickifAvailable(btnCreateAccount);		
				
	}
	
	public void createNewAccount() {
		
	}
}
