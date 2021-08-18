package automation.coreClasses;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import automation.BaseClasses.ApplicationProperties;
import automation.BaseClasses.BaseFrameworkPage;
import automation.BaseClasses.BaseTestScript;
import main.java.automation.PageObject.LoginPage;


public class BasePage<T> extends BaseFrameworkPage
{
	public Logger logger = Logger.getLogger(this.getClass());
	public String testUrl;
	public static String PCMSID;
	
	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	public BasePage<T> detectPage() throws IOException{
		String url = selenium.getCurrentUrl();
		//testUrl= ApplicationProperties.getInstance().getProperty("test.url").trim();
		testUrl= BaseTestScript.p.getProperty("test.domain").trim();
		logger.info("==================== Start Detect Page===========================");
		long time = System.currentTimeMillis();
		logger.info("application Base URL  : " + testUrl);
		logger.info("Detect Page Url : " + url);
		BasePage<T> basePagePubObject = ActionBaseClassMapping.getStartsWithBasePageDetect(selenium, url, logger);
		if(basePagePubObject != null)
			return basePagePubObject;
		basePagePubObject = getExtraMappingForBasePageDetect(url);
		logger.info("Time taken For Detect Page ====>"+(System.currentTimeMillis() - time)+" miliseconds");
		logger.info("==================== End Detect Page===========================");
		return basePagePubObject;
	}
	
	@SuppressWarnings("unchecked")
	public BasePage<T> getExtraMappingForBasePageDetect(String currentUrl) throws IOException
	{
		//String URL="https://"+ApplicationProperties.getInstance().getProperty("test.domain").trim()+"/";
		String URL="https://"+BaseTestScript.p.getProperty("test.domain").trim()+"/";
		String editId = null;
		if(currentUrl.contains("edit/"))
		{
			String[] splitValue=currentUrl.split("edit/");
			
			if(splitValue[1].contains("?"))
			{
				String[] id=splitValue[1].split("\\?");
				editId=id[0];
			}
			else
			{
				editId=splitValue[1];
			}
		}
		else if(currentUrl.contains("edit"))
		{
			String[] splitValue=currentUrl.split("edit");
			
			if(splitValue[1].contains("/"))
			{
				String[] id=splitValue[1].split("/");
				editId=id[1];
			}
		}
		
		if (currentUrl.equals(URL)){
			logger.info("Login page detected");
			return (BasePage<T>) PageFactory.initElements(selenium,LoginPage.class);
		}
		logger.info("No any Page Found");
		return null;
	}
	
	public BasePage<T> gotoBackOnBrowser() throws IOException{
		timeIntervel(2);
		selenium.navigate().back();
		timeIntervel(3);
		return detectPage();
	}

	public BasePage<T> gotoBrowserForward() throws IOException{
		timeIntervel(2);
		selenium.navigate().forward();
		timeIntervel(3);
		return detectPage();
	}
	
	public String getCurrentURL()
	{
		timeIntervel(1);
		return selenium.getCurrentUrl();
	}
}
