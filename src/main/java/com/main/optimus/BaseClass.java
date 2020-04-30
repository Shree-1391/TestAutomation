package com.main.optimus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass 
{
	public static WebDriver driver;
	public static Properties prop;
	
	public Properties initializeProperty() throws IOException
	{
		prop= new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/utildata.properties");
		
		prop.load(fis);
		return prop;
	}
	
	public WebDriver setupDriver() throws IOException
	{
		prop= initializeProperty();
	//	String browserName = prop.getProperty("browser");	
		
		String browserName = System.getProperty("browser");
	
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
			driver= new ChromeDriver();
	    }
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
	    	System.setProperty("webdriver.gecko.driver","Resources/geckodriver.exe");
	    	driver = new FirefoxDriver();
	    }
		
		else if(browserName.equalsIgnoreCase("Headless"))
		{
			System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("Headless");
			
			driver= new ChromeDriver(option);
	    }
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}
	
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		BaseClass.driver = driver;
	}
	
	public void getScreenshot(WebDriver driver, String Result) throws IOException
	{
		try {
			File srn = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
			FileUtils.copyFile(srn, new File("ScreenShot/"+Result+"Screenshot.png"));
			driver.close();
		//	Reporter.log("User Logged out application Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}	
	
}
