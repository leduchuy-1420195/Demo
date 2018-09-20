package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static WebDriver driver;
	static Properties pro;
	
	public TestBase(){
		pro = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\huy.le\\eclipse-workspace\\Demo\\src\\main\\java\\com\\qa\\properties\\config.properties");
			pro.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization(){
		
		String browserName = pro.getProperty("browser");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\huy.le\\Downloads\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\huy.le\\Downloads\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(pro.getProperty("url"));
	}
	
	public void tearDown(ITestResult result) throws IOException{
		driver.quit();
	}
	
}