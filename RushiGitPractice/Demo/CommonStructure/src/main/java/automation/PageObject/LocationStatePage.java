package main.java.automation.PageObject;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationStatePage extends CommonUtilityPage {
	
	public static String stateHeading;
	public static String stateTitle;
	public LocationStatePage(WebDriver driver) {
		super(driver);
		
	}
	
	public static final By stateHeadingLocator = By.xpath("//h1[contains(text(),'States')]"); 
	public static final By statePageStateName = By.xpath("//thead/tr[2]/td[5]/input[1]");
	public static final By statePagechkBox = By.xpath("//tr[1]/td[1]");
	public static final By statePageDeleteAllBtn = By.xpath("//button[@id='deleteAll']");
	public static final By statePageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	public static final By statepageAddNewStateBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	
	
	
	
	public void locationStateHeadingVerification() throws InterruptedException {
		Thread.sleep(3000);
		stateHeading = getText(stateHeadingLocator);
		System.out.println("----------------------------Location- State heading is :"+stateHeading);
	}
	
	public void locationStateTitleVerification() {
		
		String title =selenium.getTitle();
		stateTitle = title;
		logger.info("State page title is :"+stateTitle);
	}
	
	public boolean verifyStateData() {
		
		try {
			
		      implicitWait(5);
		      sendKeysWithEnter(statePageStateName, "junnar");
		      
		      String stateName = selenium.findElement(By.xpath("//tr[1]/td[5]")).getText();
		      logger.info("State name found on Location-State page is : "+stateName);
		      return true;
		      
			
		} catch (Exception e) {
			System.out.println("StateName junnar Not found In list");
		}
		return false;
	}
	
	public void deleteState() {
		try {
			
			implicitWait(5);
			click(statePagechkBox);
			implicitWait(5);
			click(statePageDeleteAllBtn);
			
			WebDriverWait wait=new WebDriverWait(selenium, 20);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(statePageConfirmDeleteAlert));
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void clickOnAddnewStateTypeBtn() {
		
		implicitWait(5);
		click(statepageAddNewStateBtn);
	}
}
