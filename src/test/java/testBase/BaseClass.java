package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.Date;
import org.apache.logging.log4j.LogManager;


public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
//	public LogManager logger;	// log4j
	public Properties p;

	
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
//	public void setUp(String os, String br) throws InterruptedException, IOException 
	
	public void setUp(
	        @Optional("windows") String os,
	        @Optional("chrome") String br)
	        throws InterruptedException, IOException {
		
		// Loading property file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);

		// logger=org.apache.logging.log4j.LogManager.getLogger(this.getClass());
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			
			// operating system
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			// browser
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); 
			return;
			}
			
		//	driver = new RemoteWebDriver(new URL("http://localhost:4444/ui/wd/hub"), capabilities);	
			driver = new RemoteWebDriver(new URL("http://192.168.0.107:4444/wd/hub"), capabilities);
		}
		
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name....");
			return;
			}
		}
		
		
		
	
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
	//	driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// driver.get("https://demo.opencart.com/");
	//	driver.get("https://tutorialsninja.com/demo");
		driver.get(p.getProperty("appURL")); 	// reading url form the properties file
		Thread.sleep(1000);
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphanumeric(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatedNumber);

	}

	public String captureScreen(String tname) throws IOException {
	//	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilepath = System.getProperty("user.dir")+"\\screenshots\\" +tname+ "_" +timeStamp +".png";
	//    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png";
		File targetFile = new File(targetFilepath);
		
	//	sourceFile.renameTo(targerFile);
		FileUtils.copyFile(sourceFile, targetFile);
		return targetFilepath;
		
	}
}
