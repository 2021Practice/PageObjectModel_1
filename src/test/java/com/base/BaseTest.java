package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import com.utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream inputStream;
	public static Logger logger = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	
//	private String browser;

	public void setUp(String browser) {

			System.setProperty("current.date", new Date().toString().replace(" ", "_").replace(":", "_"));
			PropertyConfigurator
					.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

			System.setProperty("org.uncommons.reportng.escape-output", "false");

			try {
				inputStream = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(inputStream);
				logger.debug("Config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//		try {
//			inputStream = new FileInputStream(
//					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			OR.load(inputStream);
//			logger.debug("OR file loaded");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Jenkin's Browser
//		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
//			browser = System.getenv("browser");
//			System.out.println("From get Env: " + System.getenv("browser"));
//			config.setProperty("browser", browser);
//		}
//
//		if (config.getProperty("browser").equals("chrome")) {
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);
				logger.debug("Chrome Launched");

			}

			// else if (config.getProperty("browser").equals("firefox")) {
			else if (browser.equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				
				FirefoxOptions firefoxOptions = new FirefoxOptions(); 
				firefoxOptions.addPreference("dom.webnotifications.enabled",false); 
				driver = new FirefoxDriver(firefoxOptions);
				logger.debug("Firefox Launched");
			}

			// else if (config.getProperty("browser").equals("opera")) {
			else if (browser.equals("opera")) {

				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
			}

			// else if (config.getProperty("browser").equals("edge")) {
			else if (browser.equals("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			logger.debug("Navigating to " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit_wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit_wait"))));
		
	}

	@AfterMethod
	public void quit() {

//		try {
//			inputStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//if (driver != null) {
			driver.quit();
			logger.debug("Execution completed");
		//}

	}
}
