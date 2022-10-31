package assignment.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import assignment.listeners.CustomListeners;
import assignment.utilities.ExcelReader;
import assignment.utilities.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties element = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\dataFile\\testdata.xlsx");
	public static WebDriverWait wait;

	public static String browser;
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			
			PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

			try
			{
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\element.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				element.load(fis);
				log.info("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = config.getProperty("browser");
				
			}
			
			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addPreference("dom.webnotifications.enabled",false);
				driver = new FirefoxDriver(firefoxOptions);
				WebDriverManager.firefoxdriver().setup();
				//driver = new FirefoxDriver();
				log.info("Firefox Launched !!!");

			} else if (config.getProperty("browser").equals("chrome")) {
				ChromeOptions o = new ChromeOptions();
				o.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(o);
				log.info("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("IE Launched !!!");

			}

			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
					TimeUnit.SECONDS);
			//wait = new WebDriverWait(driver, 5);
		}

	}
	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.close();
		}

		log.info("test execution completed !!!");
	}
}
