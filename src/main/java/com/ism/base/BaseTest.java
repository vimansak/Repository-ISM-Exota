package com.ism.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.ism.qa.util.TestUtil;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.Dimension;
import com.ism.qa.util.WebEventListener;

public class BaseTest {
	
	//public as to use in child page classes
	public static WebDriver driver;
	//java default properties class ..available under util
	public static Properties prop;
	
	//logs
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	//for recorder
	 public static ATUTestRecorder recorder;
	 
	//constructor
	public BaseTest() {
	
		//read from config.properties file
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/ism/config/config.properties");
		
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
		
	//if get exception as AUT recorder not available , edit  the .classpath file and update the location
	public static void initialization() throws ATUTestRecorderException {
		
	//----recorder	
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		  Date date = new Date();
		//Created object of ATUTestRecorder
		  //Provide path to store videos and file name format.
		  recorder = new ATUTestRecorder("D:\\ScriptVideos\\","TestVideo-"+dateFormat.format(date),false);
		  //To start video recording.
		  recorder.start();  
		
		
	
		String browsername=prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			//give the location where chromedriver downloaded .this can skip if path set to env variable
		System.setProperty("webdriver.chrome.driver", "C:/Users/d.kamaradiwala/Desktop/Selenium -TraningQA copied/ISMTest-Exota -Updated nov/chromedriver.exe");	
		
			//initialize webdriver
		    driver = new ChromeDriver();
		    driver.get(prop.getProperty("url"));
		}
		else if(browsername.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C:/Users/d.kamaradiwala/OneDrive - ISM eGroup/Vimansa -Doucments/SELENIUM/ISMTest/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		
		//logs
		e_driver = new EventFiringWebDriver(driver);
		//create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//wait time is defined under TestUtil.java class
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		       
	
		
	}
	
	public static void stoprecording() throws ATUTestRecorderException {
		recorder.stop();
	}
	
	public boolean isElementPresent(WebElement elementName, int timeout){
    	try{
    	        WebDriverWait wait = new WebDriverWait(driver, timeout);
    	        wait.until(ExpectedConditions.visibilityOf(elementName));   	      
    	        return true;
    	}catch(Exception e){
    	    return false;
    	}
}
	public boolean waitForElementToClickable(WebElement elementName, int timeout){
		try{
	        WebDriverWait wait = new WebDriverWait(driver, timeout);
	        wait.until(ExpectedConditions.elementToBeClickable(elementName));
	        return true;
		}catch(Exception e){
    	    return false;
    	}
	}
	
	//Check element is clickable and click , 60 sec timeout is added
	public void ClickifAvailable(WebElement elementName){
		try{
	        WebDriverWait wait = new WebDriverWait(driver,60);
	        wait.until(ExpectedConditions.elementToBeClickable(elementName));
	        elementName.click();
		}catch(Exception e){
			
    	    System.out.println("Element is not clickable");
    	}
	
	        
	
	}
		


}
