package main.java.automation.PageObject;



import java.io.IOException;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.coreClasses.BasePage;





public class AdminLeftPanelPage  extends BannerPage
{
	protected static final By loader=By.xpath(".//div[@class='loader']"); 
	
	public AdminLeftPanelPage(WebDriver driver) {
		super(driver);
	}
	
	public BasePage<T> gotoLeftNavigation(Object leftOption) throws IOException
	{
		String option=getOptionAsString(leftOption.toString());
		String mainMenu;
		
		if(isDisplayed(By.xpath(".//span[text()='"+option+"']//ancestor::li[@class='treeview']/a//span"))){
			mainMenu= getText(By.xpath(".//span[text()='"+option+"']//ancestor::li[@class='treeview']/a//span"));	
		}
		else{
			mainMenu= getText(By.xpath("(.//span[text()='"+option+"']//ancestor::li[contains(@class,'active')]/a//span)[1]"));
		}
		
		if(!isDisplayed(By.xpath(".//span[text()='"+option+"']//ancestor::li[contains(@class,'active')]/a//span[text()='"+mainMenu+"']"))){
			click(By.xpath(".//span[text()='"+mainMenu+"']//..//i[@class='fa fa-angle-left pull-right']"));		
		}

		String submenu =getText(By.xpath(".//li[contains(@class,'active')]//span[text()='"+option+"']//ancestor::li[contains(@class,'treeview ')][1]/a//span"));
		
		if(!(mainMenu.equals(submenu)) &&
				!isDisplayed(By.xpath(".//span[text()='"+option+"']//ancestor::li[contains(@class,'active')]/a//span[text()='"+submenu+"']"))){
				click(By.xpath(".//li[contains(@class,'active')]//span[text()='"+submenu+"']//..//i[@class='fa fa-angle-left pull-right']"));
		}
		timeIntervel(1);
		click(By.xpath(".//span[text()='"+option+"']"));
		if(countofTabs()>1){
			switchTab(true);
		}
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
		return detectPage();
	}
}
