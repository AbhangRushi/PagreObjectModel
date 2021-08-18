package automation.coreClasses;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import automation.BaseClasses.ApplicationProperties;
import automation.BaseClasses.BaseTestScript;
import main.java.automation.PageObject.AljCreatePage;
import main.java.automation.PageObject.AljPage;
import main.java.automation.PageObject.HomePage;
import main.java.automation.PageObject.LocationCityCreatePage;
import main.java.automation.PageObject.LocationCityPage;
import main.java.automation.PageObject.LocationCountryCreatePage;
import main.java.automation.PageObject.LocationCountryPage;
import main.java.automation.PageObject.LocationCountyCreatePage;
import main.java.automation.PageObject.LocationCountyPage;
import main.java.automation.PageObject.LocationStateCreatePage;
import main.java.automation.PageObject.LocationStatePage;
import main.java.automation.PageObject.LocationTaxRegionCreatePage;
import main.java.automation.PageObject.LocationTaxRegionPage;
import main.java.automation.PageObject.LocationZipCodeCreatePage;
import main.java.automation.PageObject.LocationZipCodePage;
import main.java.automation.PageObject.MenuCreatePage;
import main.java.automation.PageObject.MenuPage;
import main.java.automation.PageObject.ODAROfficeListCreatePage;
import main.java.automation.PageObject.ODAROfficeListPage;
import main.java.automation.PageObject.TimezoneCreatePage;
import main.java.automation.PageObject.TimezonePage;
import main.java.automation.PageObject.TwoStepLogin;


public class ActionBaseClassMapping
{
	static Map<String, Class<?>> pagesClassMappingMap = new LinkedHashMap<String, Class<?>>();
	
	@SuppressWarnings("unchecked")
	public static <T> BasePage<T> getStartsWithBasePageDetect(WebDriver selenium,String pageActionName,Logger logger) throws IOException 
	{	
		try
		{
			Class<?> className= findStartWithDetechPage(pagesClassMappingMap, pageActionName, logger, true);
			if(className !=null)
			{
					return (BasePage<T>) PageFactory.initElements(selenium, className);	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Class<?> findStartWithDetechPage(Map<String,Class<?>> pagesClassMappingMap,String pageActionName, Logger logger,boolean checkWithApplicationBaseURL) throws IOException
	{
		String applicationBaseURL = "https://"+BaseTestScript.p.getProperty("test.domain").trim()+"/";
		//String applicationBaseURL = "https://"+ApplicationProperties.getInstance().getProperty("test.domain").trim()+"/";
		for(Entry<String,Class<?>> e:pagesClassMappingMap.entrySet())
		{
			if(checkWithApplicationBaseURL && pageActionName.equals(applicationBaseURL+e.getKey()))
			{
				logger.info("Action name:" + pageActionName + " , Class name:" + e.getValue().getName());
				return e.getValue();
			}
		}
		return null;	
	}
	
	static
	{
		pagesClassMappingMap.put("application",HomePage.class);
		pagesClassMappingMap.put("twosteplogin",TwoStepLogin.class);
		pagesClassMappingMap.put("menus", MenuPage.class);
		pagesClassMappingMap.put("menu/create", MenuCreatePage.class);
		pagesClassMappingMap.put("odarofficelist",ODAROfficeListPage.class);
		pagesClassMappingMap.put("odarofficelist/create",ODAROfficeListCreatePage.class);
		pagesClassMappingMap.put("city",LocationCityPage.class);
		pagesClassMappingMap.put("city/create",LocationCityCreatePage.class);
		pagesClassMappingMap.put("county",LocationCountyPage.class);
		pagesClassMappingMap.put("county/create",LocationCountyCreatePage.class);
		pagesClassMappingMap.put("states",LocationStatePage.class);
		pagesClassMappingMap.put("states/create",LocationStateCreatePage.class);
		pagesClassMappingMap.put("zipcode",LocationZipCodePage.class );
		pagesClassMappingMap.put("zipcode/create", LocationZipCodeCreatePage.class);
		pagesClassMappingMap.put("country", LocationCountryPage.class);
		pagesClassMappingMap.put("country/create", LocationCountryCreatePage.class);
		pagesClassMappingMap.put("taxregion", LocationTaxRegionPage.class);
		pagesClassMappingMap.put("taxregion/create",LocationTaxRegionCreatePage.class);
		pagesClassMappingMap.put("timezones", TimezonePage.class);
		pagesClassMappingMap.put("timezone/create", TimezoneCreatePage.class);
		pagesClassMappingMap.put("alj", AljPage.class);
		pagesClassMappingMap.put("alj/create",AljCreatePage.class);
	}
}
