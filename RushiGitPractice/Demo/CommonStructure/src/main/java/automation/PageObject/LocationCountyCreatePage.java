package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationCountyCreatePage extends CommonUtilityPage {

	public LocationCountyCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locator for verify and submit to list button 
	private static final By verifyCountySubmitAndReturntoListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By verifyCountySubmitBtn =By.xpath("//*[@id='btnSubmit']");
	private static final By verifyCountyCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyCountyCancelBtn = By.xpath("//*[@id='btnCancel']");
	private static final By countyCreatepageCountryCode = By.xpath("//*[@id='CountryCode']");
	private static final By countyCreatePageState = By.xpath("//select[@id='StateCode']");
	private static final By CountyCreatepageCounty = By.xpath("//input[@id='CountyName']");
	private static final By countySubmitBtn = By.xpath("//input[@id='btnSubmit']");
	
	public boolean verifyCountySubmitAndReturnToListBtnForDisable() {
		
		if(!selenium.findElement(verifyCountySubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean verifyCountysubmitBtnForDisable() {
		
		if(!selenium.findElement(verifyCountySubmitBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public boolean verifyCountyCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyCountyCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
		
	}
	
	public boolean verifyCountyCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyCountyCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	public void addInfoInNewCounty() {
		try {
			
			implicitWait(5);
			selectValueFromDropdown(countyCreatepageCountryCode, "India - IN");
			Thread.sleep(3000);
			selectValueFromDropdown(countyCreatePageState, "Maharashtra - MH");
			Thread.sleep(2000);
			sendKeys(CountyCreatepageCounty, "pune");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// ################################## SCENARIO 07 METHODS  ############################################
	
	public void clickOnCountySubmitBtn() {
		implicitWait(5);
		click(countySubmitBtn);
	}
	
	public boolean verifyCountyForClearedForm() {
		
		WebElement element = selenium.findElement(CountyCreatepageCounty);
		String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(countyCreatePageState);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
    public void clickOnCountySubmitAndReturnToList() {
    	implicitWait(5);
    	click(verifyCountySubmitAndReturntoListBtn);
    }
    
    //################################ SCENARIO 010 METHODS #################################################
    
    public void clickOnCanceAndReturnToListBtn() {
    	
    	implicitWait(10);
    	click(verifyCountyCancelAndReturnToListBtn);
    }
}
