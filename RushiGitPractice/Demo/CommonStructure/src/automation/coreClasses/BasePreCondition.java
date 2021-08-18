package automation.coreClasses;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import automation.BaseClasses.BaseTestScript;
import automation.BaseClasses.Reporter;
import main.java.automation.PageObject.AdminLeftPanelPage;
import main.java.automation.PageObject.LoginPage;
import main.java.automation.PageObject.TwoStepLogin;


@SuppressWarnings("rawtypes")
public class BasePreCondition extends BaseTestScript
{
	public BasePage resultpage;
	protected String timeOfActionsystm;
	private static final String dateFormate = "dd MMM YYYY HH:mm";
	protected String logOutTimesystm;
	protected String[] systemlogoutTime = new String[2];
	protected String[] systemloginTime = new String[2];
	private static final By logoutButton = By.xpath(".//a[@id='logout']");
	private static final By CloseAll=By.xpath(".//div[@id='keepclient' and @aria-hidden='false']//button[@id='closeall']");
	AdminLeftPanelPage leftPanelPage;
	
	public void doLogin() throws Exception
	{
		doLogin(username, password);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePage> T doLogin(String username, String password) throws Exception
	{
		resultpage = getLoginPage();
		Reporter.log(line);
		Reporter.log("NOTE : Login With :" + username);
		Reporter.log(line);
		LoginPage loginPage= (LoginPage) resultpage;
		loginPage.loginAs(username, password);
		loginPage.clickOnSignInBtn();
		resultpage=resultpage.detectPage();
		resultpage.timeIntervel();
		resultpage.waitForParticularElement(By.id("logSys_two_step_login-token"),60);
		resultpage.timeIntervel();
		Reporter.log("twosteplogin Page Open....");
		TwoStepLogin twosteplogin=(TwoStepLogin) resultpage;
		Reporter.log("Enter Token in Twosteplogin Page");
		twosteplogin.enterToken();
		Reporter.log("Click on SignIn button");
		twosteplogin.clickOnSignInBtn();
		Reporter.log("Login Successfully....");
		timeOfActionsystm = BaseTestScript.dateAndSystemTime(dateFormate);
		systemloginTime = getAuditTime();
		setLocation();
		return (T) resultpage.detectPage();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePage> T gotoExpectedPage(String pageUrl) throws IOException
	{
		resultpage = new BasePage(selenium);
		logger.info("PAGE URL ==>" + pageUrl);
		selenium.get(pageUrl);
		timeout(1);
		return (T) resultpage.detectPage();
	}

	@SuppressWarnings("unchecked")
	private <T extends BasePage> T getLoginPage() throws IOException
	{
		Reporter.log(line);
		Reporter.log("NOTE : URL is :" + APPLICATION_URL);
		Reporter.log(line);
		logout();
		return (T) resultpage;
	}

	public void logout() throws IOException 
	{
		resultpage = new BasePage(selenium);
		resultpage = resultpage.detectPage();
		if (resultpage == null)
		{
			afterTest();
		}
		else if (resultpage !=null && !(resultpage instanceof LoginPage))
		{
			resultpage.timeIntervel(2);
			
			if(resultpage.isDisplayed(logoutButton))
			{
				resultpage.click(logoutButton);
				resultpage.timeIntervel();
				resultpage.waitForParticularElement(By.xpath(".//div[@id='keepclient' and @aria-hidden='false']"),60);
				
				if(resultpage.isDisplayed(By.xpath(".//div[@id='keepclient' and @aria-hidden='false']")))
				{
					resultpage.click(CloseAll);
					resultpage.timeIntervel(1);
					resultpage.waitUntilElementDisplays(By.xpath(".//div[@class='ajaxloader' and not(@style='display: none')]"));
					resultpage.timeIntervel(1);
				}
			}
			logOutTimesystm = BaseTestScript.dateAndSystemTime(dateFormate);
			systemlogoutTime = getAuditTime();
			resultpage = resultpage.detectPage();
		}
		logger.info("Logout Successfully...");
	}
		
	public static String[] getAuditTime()
	{
		String[] values = new String[2];
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
		Date date = new Date();
		values[0] = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 1);
		date = cal.getTime();
		values[1] = dateFormat.format(date);
		return values;
	}
	
	public BasePage gotoLeftPanelOption(Object suboptionOption) throws IOException
	{
		try{
			resultpage=resultpage.detectPage();
			leftPanelPage= (AdminLeftPanelPage) resultpage;
			resultpage=leftPanelPage.gotoLeftNavigation(suboptionOption);
		}
		catch (ClassCastException e)
		{
			logger.info("Page Not Casting");
			e.getStackTrace();
			Assert.assertTrue(false);
		}
		return resultpage;
	}
}
