package test.java.automation.tcRepository.Login;

import org.testng.annotations.Test;
import automation.BaseClasses.Reporter;
import automation.coreClasses.Assert;
import automation.coreClasses.BasePreCondition;
import automation.coreClasses.ConstantPage;
import main.java.automation.PageObject.HomePage;
import main.java.automation.PageObject.LoginPage;

public class LoginTC001 extends BasePreCondition
{	
	@Test(priority=1)
	void scenario01() throws Exception
	{
		testCaseDevelopedBy("Shakuntala Choudhary","Login with valid credential");
		testcaseID("LoginTC001");
		
		scenarioHeading(1,"Check Login functionality");
		
		Reporter.log("1. Invoking the application with URL.");
		
		logout();
		resultpage= resultpage.detectPage();
		
		expectedResult("Login Page should open");
		if(!(resultpage instanceof LoginPage))
		{
			fail("Not detect Login Page");
			Assert.assertTrue(false);
		}
		pass("Login Page is Open");
		
		doLogin();
		resultpage= resultpage.detectPage();
		
		expectedResult("Home Page should open");
		if(!(resultpage instanceof HomePage))
		{
			fail("Not detect Home Page");
			Assert.assertTrue(false);
		}
		pass("Home Page is Open");
		
		HomePage homePage= (HomePage) resultpage;
		
		expectedResult("Home Page Header should show "+ConstantPage.HOME);
		if(!homePage.verifyHeaderOfPage(ConstantPage.HOME))
		{
			fail("Home Page Header is not showing properly");
			Assert.assertTrue(false);
		}
		pass("Home Items Page Header is showing proper");
		
		expectedResult("Home Page Title should be "+ConstantPage.HOME+ConstantPage.PCMS);
		if(!homePage.getTitleOfPage().equalsIgnoreCase(ConstantPage.HOME+ConstantPage.PCMS))
		{
			fail("Home Page Title is not "+ConstantPage.HOME+ConstantPage.PCMS);
			Assert.assertTrue(false);
		}
		pass("Home Page Title is "+ConstantPage.HOME+ConstantPage.PCMS);
		
		logout();
	}
}
