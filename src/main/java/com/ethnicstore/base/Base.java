package com.ethnicstore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.Parameters;

public class Base {
	public static WebDriver driver;
	public static Properties properties;

	public Base() {

		try {
			properties = new Properties();
			FileInputStream fi = new FileInputStream(
					"C:\\Users\\Gopi Srinivas Putta\\eclipse-workspace\\EthnicStore\\src\\main\\java\\com\\ethnicstore\\config\\config.properties");
			properties.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization(String browser) throws InterruptedException {

		// String browserName = properties.getProperty("browser");

		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Gopi Srinivas Putta\\eclipse-workspace\\EthnicStore\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Gopi Srinivas Putta\\eclipse-workspace\\EthnicStore\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\Gopi Srinivas Putta\\eclipse-workspace\\EthnicStore\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(com.ethnicstore.util.Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.ethnicstore.util.Util.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(properties.getProperty("url"));

		Thread.sleep(5000);
	}

}
