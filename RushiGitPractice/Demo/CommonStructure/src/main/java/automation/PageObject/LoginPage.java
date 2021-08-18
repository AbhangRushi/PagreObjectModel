package main.java.automation.PageObject;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.BaseClasses.ApplicationProperties;
import automation.BaseClasses.BaseTestScript;
import automation.BaseClasses.Reporter;
import automation.coreClasses.Assert;

public class LoginPage extends CommonUtilityPage
{
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private static final By userName = By.id("userName");
	private static final By userPassword =By.id("password");
	
	public void loginAs(String username, String password) throws IOException
	{
		final String preLoginPage="https://"+BaseTestScript.p.getProperty("test.domain").trim()+"/";

		logger.info(" =========== In loginAs Method =========== ");
		
		if(getCurrentURL().equals(preLoginPage))
		{
			Reporter.log("Login page is open.");
			sendKeys(userName,username);
			sendKeys(userPassword, password);
		}
		else
		{
			Reporter.log("Login page is not open.");
			Assert.assertTrue(false);
		}
	}
	
	public void enterUsername(String username) throws IOException
	{
		sendKeys(userName,username);
	}
	
	public void enterPassword(String username) throws IOException
	{
		sendKeys(userPassword,username);
	}
	
//	public void clickOnLoginBtn() throws IOException 
//	{
//		click(loginBtn);
//		timeIntervel(2);
//		//waitUntilElementDisplays(loader);
////		timeIntervel();
//	}
}
