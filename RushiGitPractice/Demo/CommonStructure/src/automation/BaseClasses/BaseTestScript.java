package automation.BaseClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import automation.helper.DownloadVerifier;

public class BaseTestScript
{
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static String line = "=================================================================";
	protected JavascriptExecutor jse;
	public static WebDriver selenium;
	public static String browser;
	public static String DRIVER_Location;
	protected static String username;
	protected static String password;
	public static String APPLICATION_URL = "";
	public static String apkFile;
	public static String deviceName;
	public static String plateformVeriosn;
	public static String appPackage;
	public static String appActivity;
	public static String IP;
	private String location;
	public static String screenshotPath;
	public static Properties p;

	public BaseTestScript()
	{
	   
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/deployment/log4j.properties");
	}
	@BeforeSuite
	public void beforeSuite() throws ParseException, IOException
	{
		FileReader reader=new FileReader(System.getProperty("user.dir")+"/src/main/resources/deployment/DefaultBuild.properties");  
		 p=new Properties();  
		   p.load(reader);
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY HH:mm:ss aa");
		Reporter.log(line);
		Reporter.log("Start Time: " + dateFormat.format(cal.getTime()));
		Reporter.log(line);
		
		//readScreenShotPath();
	}
	
	@BeforeTest
	protected void beforeTest() throws IOException
	{
		getStaticData();
		try
		{         
			if (("ChromeDriver").equals(browser))
			{
				File driverPath = new File(DRIVER_Location);
				System.setProperty("webdriver.chrome.driver", driverPath.getAbsolutePath());
			   
				Map<String, Object> prefs = new HashMap<String, Object>();     // Create object of HashMap Class
				//0- Default 1- Allow  2- Block
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 ); //download multiple files
				 
				ChromeOptions options= new ChromeOptions();    // Create object of ChromeOption class
				//options.addArguments("--headless");		// For Run Automation script in headless mode
				//options.addArguments("--no-sandbox");		// For Run Automation script in in server side (CentOS)
				options.setExperimentalOption("prefs",prefs);  // Set the experimental option
				options.addArguments("disable-infobars");      //disable automation info bar
				options.addArguments("--start-maximized");     //maximize the browser when you launch it for the first time
				options.addArguments("--disable-extensions");   //Disable Notifications
				
				selenium=new ChromeDriver(options);
			}
		}
		catch (UnhandledAlertException alertException)
		{
			logger.info(alertException);
			BaseTestScript.selenium.switchTo().alert().accept();
		}
		logger.info(line);
		logger.info("TESTCASE START TIME [ BEFORE TEST ] : " + systemTime());
		logger.info(line);
		jse = (JavascriptExecutor) selenium;
	}
	
	@BeforeClass
	protected void testCaseStartTime()
	{
		Reporter.log(line);
		Reporter.log("TESTCASE START TIME [ BEFORE CLASS ] : " + systemTime());
		Reporter.log(line);
		timeout(1);
		timeout(2);
		selenium.get("https://"+APPLICATION_URL);
	
		timeout(2);
	}
	
	@BeforeMethod
	protected void beforeMethod() throws IOException
	{
		Reporter.log(line);
		Reporter.log("BeforeMethod Start TIME [BEFORE METHOD] : " + systemTime());
		Reporter.log(line);
		//timeout(1);
		//timeout(2);
		//selenium.get(APPLICATION_URL);
		//timeout(2);
	}
	
	@AfterClass
	protected void testCaseEndTime()
	{
		Reporter.log(line);
		Reporter.log("TESTCASE END TIME [AFTER CLASS] : " + systemTime());
		Reporter.log(line);
	}
	
	@AfterTest
	protected void afterTest()
	{
		try
		{	
			BaseTestScript.selenium.quit();
			logger.info("Service stop succesfully");
		}
		catch (UnhandledAlertException alert)
		{
			logger.info("Alter is exist due to some system click : " + alert);
			selenium.switchTo().alert().accept();
		}
		catch (WebDriverException exception)
		{
			logger.info("Due to some WebDriver Exception : " + exception);
		}
		catch (Exception e)
		{
			logger.info(e);
		}
	}
	
	@AfterSuite
	protected void afterSuite() throws IOException, ParseException
	{
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY HH:mm:ss");
		Reporter.log(line);
		Reporter.log("End Time: " + dateFormat.format(cal.getTime()));
		Reporter.log(line);
	}
	
	public void setLocation()
	{
		this.location = selenium.getCurrentUrl();
	}

	protected void getLocation()
	{
		selenium.get(this.location);
	}
	
	protected void expectedResult(String message)
	{
		Reporter.log("");
		Reporter.log("<b> <font color='black' size='2'>Expected :"+message+ "</font></b>");
		Reporter.log("=========");
	}
	
	protected void expectedResult()
	{
		Reporter.log("");
		Reporter.log("<b> <font color='black' size='2'>Expected :"+ "</font></b>");
		Reporter.log("=========");
	}
	
	protected void testCaseDevelopedBy(String scriptWriterName, String testCaseName)
	{
		Reporter.log(
				"<b> <font color='black' size='2'>Automation Case Developed by : " + scriptWriterName + "</font></b>");
		Reporter.log("<b> <font color='black' size='2'>TestCase Name : " + testCaseName + "</font></b>");
		Reporter.log("<b> <font color='black' size='2'>TestCase Start Time : " + systemTime() + "</font></b>");
		Reporter.log(line);
	}
	
	protected void scenarioHeading(int i, String heading)
	{
		logger.info("###########################  Start Scenario " + i + "   #####################");
		Reporter.log("<b> <font color='black' size='2'>Scenario " + i + " : " + heading + "</font></b>");
		Reporter.log("");
	}
	
	protected void scenarioSection(String heading)
	{
		Reporter.log("<b> <font color='black' size='2'>Sceanrio Area/Page Name:\n" + heading + "</font></b>");
		Reporter.log(line);
		Reporter.log("");
	}
	
	protected void testcaseID(String testcaseID)
	{
		Reporter.log("<b> <font color='black' size='2'>TestcaseID:\n" + testcaseID + "</font></b>");
		Reporter.log(line);
		Reporter.log("");
	}
	
	private void getStaticData() throws IOException
	{
		username = getUsername();
		password = getPassword();
		APPLICATION_URL = p.getProperty("test.domain").trim();
		DRIVER_Location =  p.getProperty("driver.location").trim()+"/";
		browser = p.getProperty("selenium.browser.driver").trim().trim();
//		APPLICATION_URL = ApplicationProperties.getInstance().getProperty("test.url").trim();
//		DRIVER_Location = ApplicationProperties.getInstance().getProperty("driver.location").trim()+"/";
//		browser = ApplicationProperties.getInstance().getProperty("selenium.browser.driver").trim();
		
		logger.info("====================================== Local Machine DETAILS ===================================");
		logger.info("Url is detected from property file : " + APPLICATION_URL);
		logger.info("Username is detected from property file : " + username);
		logger.info("Password is detected from property file : " + password);
		logger.info("Browser is Detected : " + browser);
		logger.info("Current Project : " + DRIVER_Location);
		logger.info(line);
	}
	
	protected String getUsername() throws IOException
	{
		return p.getProperty("setUsername").trim();
		//return ApplicationProperties.getInstance().getProperty("test.username").trim();
	}
	
	protected String getPassword() throws IOException
	{
		return p.getProperty("setPassword").trim();
		//return ApplicationProperties.getInstance().getProperty("test.password").trim();
	}
	
	protected static String systemTime()
	{
		Calendar calendar = new GregorianCalendar();
		String amPm;
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0)
		{
			amPm = "AM";
		}
		else
		{
			amPm = "PM";
		}
		return hour + "_" + minute + "_" + second + "_" + amPm;
	}

	protected void timeout(long second)
	{
		try
		{
			Thread.sleep(second * 600);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
	}
	
	public static String dateAndSystemTime(String dateAndTimeFormat)
	{
		DateFormat dateFormat = new SimpleDateFormat(dateAndTimeFormat);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void pass(String ExpectedDescription)
	{
		Reporter.log(line);
		Reporter.log("<b> <font color='green' size='2'>PASS :" + ExpectedDescription + "</font></b>");
		Reporter.log(line);
	}
	
	public void fail(String ExpectedDescription)
	{
		Reporter.log(line);
		Reporter.log("<b> <font color='red' size='2'>FAIL :" + ExpectedDescription + "</font></b>");
		Reporter.log(line);
	}
	
	protected void BugReport(String bugDescription)
	{
		Reporter.log(line);
		Reporter.log("<b> <font color='red' size='2'>BUG DESCRIPTION :" + bugDescription + "</font></b>");
		Reporter.log(line);
	}
	
	private void readScreenShotPath()
	{
		DownloadVerifier doc = new DownloadVerifier();
		//File file = new File("../dist/screenshot.txt");
		File file = new File(System.getProperty("user.dir")+"/screenshot.txt");
		
		try
		{
			screenshotPath = doc.ReadTextFile(file.getCanonicalPath());
		}
		catch (IOException e)
		{
			logger.info("screenshot in disable foam : " + e);
		}
	}
	
	protected void precondition(String precondition)
	{
		Reporter.log("");
		Reporter.log(line);
		Reporter.log("<b> <font color='black' size='2'>Precondition:\n" + precondition + "</font></b>");
		Reporter.log(line);
		Reporter.log("");
	}
	
	protected void postcondition(String precondition)
	{
		Reporter.log("");
		Reporter.log(line);
		Reporter.log("<b> <font color='black' size='2'>Postcondition:\n" + precondition + "</font></b>");
		Reporter.log(line);
		Reporter.log("");
	}
}
