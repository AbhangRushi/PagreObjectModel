package main.java.automation.PageObject;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import automation.coreClasses.BasePage;

public class BannerPage extends BasePage<T>
{	
	public BannerPage(WebDriver driver) {
		super(driver);
	}
	
	public String getOptionAsString(String adminOption)
	{
		String option = adminOption;
		if (adminOption.contains("_") || adminOption.contains("__") || adminOption.contains("$") || adminOption.contains("$$"))
		{
			option = option.replace("____", " & ");
			option = option.replace("___", "7");
			option = option.replace("__", " - ");
			option = option.replace("_", " ");
			option = option.replace("$$$$", "-");
			option = option.replace("$$$", ", ");
			option = option.replace("$$", "'");
			option = option.replace("$", "/");
		}
		return option;
	}
}
